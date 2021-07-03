package com.currency.sys.dto;

import com.currency.sys.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 角色表
    * </p>
*
* @author admin
* @since 2021-07-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRole对象", description="角色表")
public class SysRoleDTO extends BaseDTO implements Serializable {

private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "角色ID")

private String roleId;

@ApiModelProperty(value = "角色名称")
private String roleName;

@ApiModelProperty(value = "角色编码")
private String roleCode;




}
