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
@ApiModel(value="SysMenu对象", description="")
public class SysMenuDTO extends BaseDTO {

@ApiModelProperty(value = "主键")

private String menuId;

@ApiModelProperty(value = "菜单名称")
private String menuName;

@ApiModelProperty(value = "父节点ID")
private String parentMenuId;

@ApiModelProperty(value = "排序")
private Integer menuSort;

@ApiModelProperty(value = "菜单编码")
private String menuSn;

@ApiModelProperty(value = "菜单地址")
private String menuUrl;

@ApiModelProperty(value = "菜单图标")
private String menuIcon;

@ApiModelProperty(value = "菜单类型：1菜单，2：按钮")
private String menuType;




}
