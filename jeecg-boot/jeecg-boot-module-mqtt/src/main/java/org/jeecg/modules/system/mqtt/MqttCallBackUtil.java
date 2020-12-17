package org.jeecg.modules.system.mqtt;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.eclipse.paho.client.mqttv3.*;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.system.entity.MbpMqttConfig;
import org.jeecg.modules.system.entity.MbpMqttData;
import org.jeecg.modules.system.service.IMbpMqttConfigService;
import org.jeecg.modules.system.service.IMbpMqttDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

import java.text.MessageFormat;
import java.util.List;

/**
 * 杭州三米明德科技有限公司
 * MQTT客户端回调函数
 * @author zzm
 * date 2020-12-01
 */
@Slf4j
@Component
public class MqttCallBackUtil implements MqttCallback {

    @Autowired
    private IMbpMqttConfigService mbpMqttConfigService;
    @Autowired
    private IMbpMqttDataService mbpMqttDataService;
    @Autowired
    private RedisUtil redisUtil;

    private MqttClient mqttConnect = null;
    private MbpMqttConfig mqttConfig = null;

    public static MqttCallBackUtil util;
    @PostConstruct
    public void init() {
        util = this;
        util.mbpMqttConfigService = this.mbpMqttConfigService;
        util.mbpMqttDataService = this.mbpMqttDataService;
        util.redisUtil = this.redisUtil;
    }


    /**
     * 连接丢失
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        /*连接断开时清除缓存*/
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.del(mqttConfig.getMqttClientId());
        MQTTConnentionUtil.reconnectionMQTT(mqttConnect, mqttConfig, this, mbpMqttConfigService);
    }

    /**
     *
     * @param topic
     * @param mqttMessage
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        //mqttConnect.disconnect();
        log.info("=========================================================》" + mqttConfig.getMqttClientId() +"接收消息成功！当前消息主题 : " + topic);
        System.out.println("===========》" + mqttConfig.getMqttClientId() + "接收消息成功！ 原始格式: " + mqttMessage);
        String theMsg = MessageFormat.format("{0}", new String(mqttMessage.getPayload()));

        JSONObject jsonObject = JSONObject.parseObject(theMsg);
        String devList = jsonObject.getString("devList");
        if (StringUtils.isEmpty(devList)) {
            System.out.println("网关可能掉线");
            return;
        }
        JSONArray devListArray= JSONArray.parseArray(devList);
        // 获取设备ID
        String devNo = devListArray.getJSONObject(0).getString("devNo");
        log.info("===========》" + mqttConfig.getMqttClientId() + "接收消息成功！ 原始格式，devNo: " + devNo);
        if (!StringUtils.isEmpty(devNo)) {
            // 获取数据列表
            JSONArray varList = devListArray.getJSONObject(0).getJSONArray("varList");
            List<MbpMqttData> mbpMqttDataList = JSONArray.parseArray(varList.toJSONString(), MbpMqttData.class);
            mbpMqttDataList.forEach(item -> {
                //System.out.println("var Name：" + item.getVarName());
                // 客户端ID
                item.setConfigId(mqttConfig.getId());
                // 设备ID
                item.setEquipmentId(devNo);
                util.mbpMqttDataService.save(item);
            });
            //util.mbpMqttDataService.saveBatch(mbpMqttDataList);
            //log.info("===========》" + mqttConfig.getMqttClientId() + "接收消息成功！ : " + devListArray.getJSONObject(0).getJSONArray("varList"));
        }
    }

    /**
     *  发布消息
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    /**
     * 开启连接
     */
    //@PostConstruct
    public void  run(MbpMqttConfig mqtt) throws MqttException {
        mqttConfig = mqtt;
        mqttConnect = MQTTConnentionUtil.getMQTTConnect(mqtt);
        mqttConnect.setCallback(this);
        // 连接成功， 手动设置连接状态，delFlag
        MbpMqttConfig newMqtt = new MbpMqttConfig();
        newMqtt.setId(mqtt.getId());
        newMqtt.setDelFlag(1);
        util.mbpMqttConfigService.updateById(newMqtt);
        //RedisUtil redisUtil = new RedisUtil();
        //将连接存入缓存
        //util.redisUtil.set(mqtt.getMqttClientId(), mqttConnect, 0);
    }
}
