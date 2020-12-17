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
 * @Description: 公司
 * @Author: jeecg-boot
 * @Date:   2020-12-01
 * @Version: V1.0
 */
@Data
@TableName("mbp_customer")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mbp_customer对象", description="公司")
public class MbpCustomer implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String customerName;
	/**简称*/
	@Excel(name = "简称", width = 15)
    @ApiModelProperty(value = "简称")
    private java.lang.String abcName;
	/**检索码*/
	@Excel(name = "检索码", width = 15)
    @ApiModelProperty(value = "检索码")
    private java.lang.String indexCode;
	/**isSupplier*/
	@Excel(name = "isSupplier", width = 15)
    @ApiModelProperty(value = "isSupplier")
    private java.lang.Integer isSupplier;
	/**isClient*/
	@Excel(name = "isClient", width = 15)
    @ApiModelProperty(value = "isClient")
    private java.lang.Integer isClient;
	/**行业类型*/
	@Excel(name = "行业类型", width = 15)
    @ApiModelProperty(value = "行业类型")
    private java.lang.String customerType;
	/**categoryType*/
	@Excel(name = "categoryType", width = 15)
    @ApiModelProperty(value = "categoryType")
    private java.lang.String categoryType;
	/**地区*/
	@Excel(name = "地区", width = 15)
    @ApiModelProperty(value = "地区")
    private java.lang.String customerArea;
	/**firstCoDate*/
	@Excel(name = "firstCoDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "firstCoDate")
    private java.util.Date firstCoDate;
	/**categoryType2*/
	@Excel(name = "categoryType2", width = 15)
    @ApiModelProperty(value = "categoryType2")
    private java.lang.String categoryType2;
	/**isAssist*/
	@Excel(name = "isAssist", width = 15)
    @ApiModelProperty(value = "isAssist")
    private java.lang.Integer isAssist;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private java.lang.String customerAddress;
	/**postCode*/
	@Excel(name = "postCode", width = 15)
    @ApiModelProperty(value = "postCode")
    private java.lang.String postCode;
	/**customerManager*/
	@Excel(name = "customerManager", width = 15)
    @ApiModelProperty(value = "customerManager")
    private java.lang.String customerManager;
	/**联系人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
    private java.lang.String contactPerson;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
    private java.lang.String telephone;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;
	/**传真*/
	@Excel(name = "传真", width = 15)
    @ApiModelProperty(value = "传真")
    private java.lang.String fax;
	/**银行账号*/
	@Excel(name = "银行账号", width = 15)
    @ApiModelProperty(value = "银行账号")
    private java.lang.String bank;
	/**account*/
	@Excel(name = "account", width = 15)
    @ApiModelProperty(value = "account")
    private java.lang.String account;
	/**paymentKind*/
	@Excel(name = "paymentKind", width = 15)
    @ApiModelProperty(value = "paymentKind")
    private java.lang.String paymentKind;
	/**paymentCircle*/
	@Excel(name = "paymentCircle", width = 15)
    @ApiModelProperty(value = "paymentCircle")
    private java.lang.Integer paymentCircle;
	/**currency*/
	@Excel(name = "currency", width = 15)
    @ApiModelProperty(value = "currency")
    private java.lang.String currency;
	/**税号*/
	@Excel(name = "税号", width = 15)
    @ApiModelProperty(value = "税号")
    private java.lang.String taxNo;
	/**license*/
	@Excel(name = "license", width = 15)
    @ApiModelProperty(value = "license")
    private java.lang.String license;
	/**creditGrade*/
	@Excel(name = "creditGrade", width = 15)
    @ApiModelProperty(value = "creditGrade")
    private java.lang.String creditGrade;
	/**creditLimit*/
	@Excel(name = "creditLimit", width = 15)
    @ApiModelProperty(value = "creditLimit")
    private java.math.BigDecimal creditLimit;
	/**salesman*/
	@Excel(name = "salesman", width = 15)
    @ApiModelProperty(value = "salesman")
    private java.lang.String salesman;
	/**customerStatus*/
	@Excel(name = "customerStatus", width = 15)
    @ApiModelProperty(value = "customerStatus")
    private java.lang.Integer customerStatus;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
    private java.lang.String description;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String disp;
	/**网址*/
	@Excel(name = "网址", width = 15)
    @ApiModelProperty(value = "网址")
    private java.lang.String webSite;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
	/**是否删除*/
	@Excel(name = "是否删除", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "是否删除")
    private java.lang.Integer delFlag;
}
