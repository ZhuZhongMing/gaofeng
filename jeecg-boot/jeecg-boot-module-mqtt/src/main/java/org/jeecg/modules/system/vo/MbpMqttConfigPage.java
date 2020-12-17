package org.jeecg.modules.system.vo;

import java.util.List;
import org.jeecg.modules.system.entity.MbpMqttConfig;
import org.jeecg.modules.system.entity.MbpMqttTopics;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: MQTT配置信息
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Data
@ApiModel(value="mbp_mqtt_configPage对象", description="MQTT配置信息")
public class MbpMqttConfigPage {

	/**编号*/
	@ApiModelProperty(value = "编号")
	private String id;
	/**公司名称*/
	@Excel(name = "公司名称", width = 15, dictTable = "mbp_customer", dicText = "customer_name", dicCode = "id")
    @Dict(dictTable = "mbp_customer", dicText = "customer_name", dicCode = "id")
	@ApiModelProperty(value = "公司名称")
	private String customerId;
	/**主机地址(例：tcp://127.0.0.1:1883)*/
	@Excel(name = "主机地址(例：tcp://127.0.0.1:1883)", width = 15)
	@ApiModelProperty(value = "主机地址(例：tcp://127.0.0.1:1883)")
	private String mqttHost;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
	@ApiModelProperty(value = "用户名")
	private String mqttUsername;
	/**密码*/
	@Excel(name = "密码", width = 15)
	@ApiModelProperty(value = "密码")
	private String mqttPassword;
	/**客户端ID*/
	@Excel(name = "客户端ID", width = 15)
	@ApiModelProperty(value = "客户端ID")
	private String mqttClientId;
	/**说明*/
	@Excel(name = "说明", width = 15)
	@ApiModelProperty(value = "说明")
	private String disp;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15, dicCode = "del_flag")
    @Dict(dicCode = "del_flag")
	@ApiModelProperty(value = "是否删除")
	private Integer delFlag;
	
	@ExcelCollection(name="主题")
	@ApiModelProperty(value = "主题")
	private List<MbpMqttTopics> mbpMqttTopicsList;
	
}
