package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MbpMqttData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 实时数据
 * @Author: jeecg-boot
 * @Date:   2020-12-03
 * @Version: V1.0
 */
public interface MbpMqttDataMapper extends BaseMapper<MbpMqttData> {

    /**
     * 查询最新10条记录
     * @param mbpMqttData
     * @return
     */
    List<MbpMqttData> TopTen(MbpMqttData mbpMqttData);

}
