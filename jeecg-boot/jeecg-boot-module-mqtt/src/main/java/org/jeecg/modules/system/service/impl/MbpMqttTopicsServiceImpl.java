package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpMqttTopics;
import org.jeecg.modules.system.mapper.MbpMqttTopicsMapper;
import org.jeecg.modules.system.service.IMbpMqttTopicsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 主题
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Service
public class MbpMqttTopicsServiceImpl extends ServiceImpl<MbpMqttTopicsMapper, MbpMqttTopics> implements IMbpMqttTopicsService {
	
	@Autowired
	private MbpMqttTopicsMapper mbpMqttTopicsMapper;
	
	@Override
	public List<MbpMqttTopics> selectByMainId(String mainId) {
		return mbpMqttTopicsMapper.selectByMainId(mainId);
	}
}
