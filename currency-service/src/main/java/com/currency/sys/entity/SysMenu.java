package com.currency.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="SysMenu对象", description="")
public class SysMenu implements Serializable {

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

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = " 状态")
    private String statusCd;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    private String remark;


    public static final String MENU_ID = "menu_id";

    public static final String MENU_NAME = "menu_name";

    public static final String PARENT_MENU_ID = "parent_menu_id";

    public static final String MENU_SORT = "menu_sort";

    public static final String MENU_SN = "menu_sn";

    public static final String MENU_URL = "menu_url";

    public static final String MENU_ICON = "menu_icon";

    public static final String MENU_TYPE = "menu_type";

    public static final String TENANT_ID = "tenant_id";

    public static final String STATUS_CD = "status_cd";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String REMARK = "remark";

}
