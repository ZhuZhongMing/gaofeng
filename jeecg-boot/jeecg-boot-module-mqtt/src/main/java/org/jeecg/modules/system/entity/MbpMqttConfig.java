package org.jeecg.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: MQTT配置信息
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@ApiModel(value="mbp_mqtt_config对象", description="MQTT配置信息")
@Data
@TableName("mbp_mqtt_config")
public class MbpMqttConfig implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private String id;
	/**公司名称*/
	@Excel(name = "公司名称", width = 15, dictTable = "mbp_customer", dicText = "customer_name", dicCode = "id")
    @Dict(dictTable = "mbp_customer", dicText = "customer_name", dicCode = "id")
    @ApiModelProperty(value = "公司名称")
    private String customerId;
	/**主机地址(例：tcp://127.0.0.1:1883)*/
	@Excel(name = "主机地址(例：tcp://127.0.0.1:1883)", width = 15)
    @ApiModelProperty(value = "主机地址(例：tcp://127.0.0.1:1883)")
    private String mqttHost;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private String mqttUsername;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private String mqttPassword;
	/**客户端ID*/
	@Excel(name = "客户端ID", width = 15)
    @ApiModelProperty(value = "客户端ID")
    private String mqttClientId;
	/**说明*/
	@Excel(name = "说明", width = 15)
    @ApiModelProperty(value = "说明")
    private String disp;
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
	/**是否删除*/
    /**
     * 替用： 0 - 未开启订阅，1 - 以开启订阅
     */
	@Excel(name = "是否删除", width = 15, dicCode = "del_flag")
    @Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "是否删除")
    private Integer delFlag;

    /**
     * 多个主题组成的数组，用于存储对应客户端下的所有主题
     */
	@TableField(exist = false)
    private String[] topics;
}
