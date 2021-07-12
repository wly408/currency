package com.currency.dto.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
    * 租户表
    * </p>
*
* @author admin
* @since 2021-07-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysTenant对象", description="租户表")
public class SysTenantDTO implements Serializable {

private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "租户ID")

private String tenantId;

@ApiModelProperty(value = "租户编码")
private String tenantCode;

@ApiModelProperty(value = "租户名称")
private String tenantName;

@ApiModelProperty(value = "创建时间")
private Date createDate;

@ApiModelProperty(value = "创建人")
private String createUser;

@ApiModelProperty(value = "状态")
private String statusCd;

@ApiModelProperty(value = "修改时间")
private Date updateDate;

@ApiModelProperty(value = "修改人")
private String updateUser;

@ApiModelProperty(value = "备注")
private String remark;



}
