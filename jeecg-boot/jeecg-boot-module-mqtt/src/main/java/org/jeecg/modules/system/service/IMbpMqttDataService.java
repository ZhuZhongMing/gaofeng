package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpMqttData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 实时数据
 * @Author: jeecg-boot
 * @Date:   2020-12-03
 * @Version: V1.0
 */
public interface IMbpMqttDataService extends IService<MbpMqttData> {

    /**
     * 查询最新10条记录
     * @param mbpMqttData
     * @return
     */
    List<MbpMqttData> TopTen(MbpMqttData mbpMqttData);
}
