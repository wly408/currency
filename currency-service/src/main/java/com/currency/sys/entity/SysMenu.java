package com.currency.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.currency.common.entity.BaseEntity;
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
public class SysMenu extends BaseEntity  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "menu_id", type = IdType.INPUT)
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

    public static final String MENU_ID = "menu_id";

    public static final String MENU_NAME = "menu_name";

    public static final String PARENT_MENU_ID = "parent_menu_id";

    public static final String MENU_SORT = "menu_sort";

    public static final String MENU_SN = "menu_sn";

    public static final String MENU_URL = "menu_url";

    public static final String MENU_ICON = "menu_icon";

    public static final String MENU_TYPE = "menu_type";

}
