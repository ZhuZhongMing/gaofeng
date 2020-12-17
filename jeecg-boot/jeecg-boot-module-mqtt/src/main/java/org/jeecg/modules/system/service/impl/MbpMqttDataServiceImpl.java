package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpMqttData;
import org.jeecg.modules.system.mapper.MbpMqttDataMapper;
import org.jeecg.modules.system.service.IMbpMqttDataService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 实时数据
 * @Author: jeecg-boot
 * @Date:   2020-12-03
 * @Version: V1.0
 */
@Service
public class MbpMqttDataServiceImpl extends ServiceImpl<MbpMqttDataMapper, MbpMqttData> implements IMbpMqttDataService {

    @Resource
    private MbpMqttDataMapper mbpMqttDataMapper;

    @Override
    public List<MbpMqttData> TopTen(MbpMqttData mbpMqttData) {
        return mbpMqttDataMapper.TopTen(mbpMqttData);
    }
}
