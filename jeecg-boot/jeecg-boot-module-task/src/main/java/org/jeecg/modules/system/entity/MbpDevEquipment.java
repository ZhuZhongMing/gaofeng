package org.jeecg.modules.system.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 生产设备
 * @Author: jeecg-boot
 * @Date:   2020-12-04
 * @Version: V1.0
 */
@Data
@TableName("mbp_dev_equipment")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mbp_dev_equipment对象", description="生产设备")
public class MbpDevEquipment implements Serializable {
    private static final long serialVersionUID = 1L;

	/**设备编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "设备编号")
    private String id;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "所属部门")
    private String deptId;
	/**设备容量*/
	@Excel(name = "设备容量", width = 15)
    @ApiModelProperty(value = "设备容量")
    private String equipmentCapacity;
	/**温度上限报警*/
	@Excel(name = "温度上限报警", width = 15)
    @ApiModelProperty(value = "温度上限报警")
    private Integer temperatureUp;
	/**温度下限报警*/
	@Excel(name = "温度下限报警", width = 15)
    @ApiModelProperty(value = "温度下限报警")
    private Integer temperatureDown;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
    private Integer delFlag;
	/**设备状态0-停机,1-运行*/
	@Excel(name = "设备状态0-停机,1-运行", width = 15, dicCode = "equipment_status")
	@Dict(dicCode = "equipment_status")
    @ApiModelProperty(value = "设备状态0-停机,1-运行")
    private Integer equipmentStatus;
}
