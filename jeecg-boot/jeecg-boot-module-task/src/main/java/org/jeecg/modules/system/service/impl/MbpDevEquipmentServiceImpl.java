package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpDevEquipment;
import org.jeecg.modules.system.entity.MbpMqttData;
import org.jeecg.modules.system.mapper.MbpDevEquipmentMapper;
import org.jeecg.modules.system.service.IMbpDevEquipmentService;
import org.jeecg.modules.system.vo.MbpDevEquipmentVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 生产设备
 * @Author: jeecg-boot
 * @Date:   2020-12-04
 * @Version: V1.0
 */
@Service
public class MbpDevEquipmentServiceImpl extends ServiceImpl<MbpDevEquipmentMapper, MbpDevEquipment> implements IMbpDevEquipmentService {

    @Resource
    private MbpDevEquipmentMapper mbpDevEquipmentMapper;

    @Override
    public List<MbpDevEquipmentVO> queryDevEquipmentList(MbpDevEquipment mbpDevEquipment) {
        return mbpDevEquipmentMapper.queryDevEquipmentList(mbpDevEquipment);
    }

    @Override
    public List<MbpMqttData> queryLastVarValue(MbpDevEquipment mbpDevEquipment) {
        return mbpDevEquipmentMapper.queryLastVarValue(mbpDevEquipment);
    }

    @Override
    public List<MbpMqttData> queryLastTemperature(MbpDevEquipment mbpDevEquipment) {
        return mbpDevEquipmentMapper.queryLastTemperature(mbpDevEquipment);
    }

    @Override
    public List<MbpDevEquipmentVO> queryAllDevEquipmentList(MbpDevEquipment mbpDevEquipment) {
        return mbpDevEquipmentMapper.queryAllDevEquipmentList(mbpDevEquipment);
    }
}
