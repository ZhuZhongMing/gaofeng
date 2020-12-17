package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MbpMqttConfig;
import org.jeecg.modules.system.entity.MbpMqttTopics;
import org.jeecg.modules.system.mapper.MbpMqttTopicsMapper;
import org.jeecg.modules.system.mapper.MbpMqttConfigMapper;
import org.jeecg.modules.system.service.IMbpMqttConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: MQTT配置信息
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Service
public class MbpMqttConfigServiceImpl extends ServiceImpl<MbpMqttConfigMapper, MbpMqttConfig> implements IMbpMqttConfigService {

	@Autowired
	private MbpMqttConfigMapper mbpMqttConfigMapper;
	@Autowired
	private MbpMqttTopicsMapper mbpMqttTopicsMapper;
	
	@Override
	@Transactional
	public void saveMain(MbpMqttConfig mbpMqttConfig, List<MbpMqttTopics> mbpMqttTopicsList) {
		mbpMqttConfigMapper.insert(mbpMqttConfig);
		if(mbpMqttTopicsList!=null && mbpMqttTopicsList.size()>0) {
			for(MbpMqttTopics entity:mbpMqttTopicsList) {
				//外键设置
				entity.setMqttConfigId(mbpMqttConfig.getId());
				mbpMqttTopicsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(MbpMqttConfig mbpMqttConfig,List<MbpMqttTopics> mbpMqttTopicsList) {
		mbpMqttConfigMapper.updateById(mbpMqttConfig);
		
		//1.先删除子表数据
		mbpMqttTopicsMapper.deleteByMainId(mbpMqttConfig.getId());
		
		//2.子表数据重新插入
		if(mbpMqttTopicsList!=null && mbpMqttTopicsList.size()>0) {
			for(MbpMqttTopics entity:mbpMqttTopicsList) {
				//外键设置
				entity.setMqttConfigId(mbpMqttConfig.getId());
				mbpMqttTopicsMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mbpMqttTopicsMapper.deleteByMainId(id);
		mbpMqttConfigMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mbpMqttTopicsMapper.deleteByMainId(id.toString());
			mbpMqttConfigMapper.deleteById(id);
		}
	}
	
}
