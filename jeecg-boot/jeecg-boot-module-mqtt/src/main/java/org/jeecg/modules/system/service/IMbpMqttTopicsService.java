package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MbpMqttTopics;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 主题
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
public interface IMbpMqttTopicsService extends IService<MbpMqttTopics> {

	public List<MbpMqttTopics> selectByMainId(String mainId);
}
