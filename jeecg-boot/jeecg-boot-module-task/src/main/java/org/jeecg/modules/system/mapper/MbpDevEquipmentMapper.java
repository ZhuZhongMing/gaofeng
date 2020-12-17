package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MbpDevEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.entity.MbpMqttData;
import org.jeecg.modules.system.vo.MbpDevEquipmentVO;

/**
 * @Description: 生产设备
 * @Author: jeecg-boot
 * @Date:   2020-12-04
 * @Version: V1.0
 */
public interface MbpDevEquipmentMapper extends BaseMapper<MbpDevEquipment> {

    /**
     * 查询生产设备温度
     * @param mbpDevEquipment
     * @return
     */
    List<MbpDevEquipmentVO> queryDevEquipmentList(MbpDevEquipment mbpDevEquipment);

    /**
     * 查询设备最新数据
     * @param mbpDevEquipment
     * @return
     */
    List<MbpMqttData> queryLastVarValue(MbpDevEquipment mbpDevEquipment);

    /**
     * 查询控制设备温度
     * @param mbpDevEquipment
     * @return
     */
    List<MbpMqttData> queryLastTemperature(MbpDevEquipment mbpDevEquipment);

    /**
     * 查询所有生产设备温度变化
     * @param mbpDevEquipment
     * @return
     */
    List<MbpDevEquipmentVO> queryAllDevEquipmentList(MbpDevEquipment mbpDevEquipment);

}
