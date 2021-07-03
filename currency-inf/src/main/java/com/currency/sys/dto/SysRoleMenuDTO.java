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
    * 
    * </p>
*
* @author admin
* @since 2021-07-03
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRoleMenu对象", description="")
public class SysRoleMenuDTO extends BaseDTO implements Serializable {

private static final long serialVersionUID = 1L;

@ApiModelProperty(value = "主键")
private String id;

@ApiModelProperty(value = "角色ID")
private String roleId;

@ApiModelProperty(value = "菜单ID")
private String menuId;



}
