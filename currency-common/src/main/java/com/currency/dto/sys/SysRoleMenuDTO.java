package com.currency.dto.sys;

import com.currency.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* <p>
    * 
    * </p>
*
* @author admin
* @since 2021-07-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleMenu对象", description="")
public class SysRoleMenuDTO extends BaseDTO {

@ApiModelProperty(value = "主键")
private String id;

@ApiModelProperty(value = "角色ID")
private String roleId;

@ApiModelProperty(value = "菜单ID")
private String menuId;



}
