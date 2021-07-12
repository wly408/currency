package com.currency.dto.sys;

import com.currency.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="SysRole对象", description="角色表")
public class SysRoleDTO extends BaseDTO {


@ApiModelProperty(value = "角色ID")

private String roleId;

@ApiModelProperty(value = "角色名称")
private String roleName;

@ApiModelProperty(value = "角色编码")
private String roleCode;




}
