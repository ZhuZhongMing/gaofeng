package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.MbpMqttTopics;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 生产设备
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Data
@ApiModel(value="mbp_mqtt_configPage对象", description="MQTT配置信息")
public class MbpDevEquipmentVO {

	/**设备编号*/
	@ApiModelProperty(value = "设备编号")
	private String id;
	/**设备名称*/
	@ApiModelProperty(value = "设备名称")
	private String equipmentName;
	/**设备类型*/
	@ApiModelProperty(value = "设备类型")
	private String equipmentType;
	/**所属部门*/
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@ApiModelProperty(value = "所属部门")
	private String deptId;
	/**设备容量*/
	@ApiModelProperty(value = "设备容量")
	private String equipmentCapacity;
	/**温度上限报警*/
	@ApiModelProperty(value = "温度上限报警")
	private Integer temperatureUp;
	/**温度下限报警*/
	@ApiModelProperty(value = "温度下限报警")
	private Integer temperatureDown;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Dict(dicCode = "del_flag")
	@ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**设备状态0-停机,1-运行*/
	@Dict(dicCode = "equipment_status")
	@ApiModelProperty(value = "设备状态0-停机,1-运行")
	private Integer equipmentStatus;

	/**变量值*/
	@ApiModelProperty(value = "变量值")
	private String varValue;
	/**变量名*/
	@ApiModelProperty(value = "变量值")
	private String varName;
	/**生产设备编号*/
	@ApiModelProperty(value = "生产设备编号")
	private String equipmentId;

	/**变量中文含义*/
	@ApiModelProperty(value = "变量中文含义")
	private String dict;

	@ApiModelProperty(value = "温度")
	private String temperature;

	@ApiModelProperty(value = "液位")
	private String liquidLevel;

	@ApiModelProperty(value = "CO2浓度")
	private String carbonDioxide;


	@ApiModelProperty(value = "水阀关")
	private String waterValveClose;
	@ApiModelProperty(value = "水阀开")
	private String waterValveOpen;
	@ApiModelProperty(value = "关时间")
	private String closeTime;
	@ApiModelProperty(value = "开时间")
	private String openTime;
	@ApiModelProperty(value = "实采")
	private String actualMining;
	@ApiModelProperty(value = "回差")
	private String returnDifference;

	@ApiModelProperty(value = "冰水温度")
	private String iceWaterTemperature;
	@ApiModelProperty(value = "糖化温度")
	private String saccharificationTemperature;
	@ApiModelProperty(value = "过滤温度")
	private String filtrationTemperature;

	
}
