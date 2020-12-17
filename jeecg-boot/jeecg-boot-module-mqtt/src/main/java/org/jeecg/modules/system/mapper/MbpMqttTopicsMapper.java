package org.jeecg.modules.system.mapper;

import java.util.List;
import org.jeecg.modules.system.entity.MbpMqttTopics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 主题
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
public interface MbpMqttTopicsMapper extends BaseMapper<MbpMqttTopics> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<MbpMqttTopics> selectByMainId(@Param("mainId") String mainId);
}
