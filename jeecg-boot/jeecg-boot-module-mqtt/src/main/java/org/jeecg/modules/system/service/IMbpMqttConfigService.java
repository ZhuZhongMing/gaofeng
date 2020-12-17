package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpMqttTopics;
import org.jeecg.modules.system.entity.MbpMqttConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: MQTT配置信息
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
public interface IMbpMqttConfigService extends IService<MbpMqttConfig> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MbpMqttConfig mbpMqttConfig, List<MbpMqttTopics> mbpMqttTopicsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MbpMqttConfig mbpMqttConfig, List<MbpMqttTopics> mbpMqttTopicsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
