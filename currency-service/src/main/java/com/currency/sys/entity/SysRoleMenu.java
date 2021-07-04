package com.currency.sys.entity;

import com.currency.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="SysRoleMenu对象", description="")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "菜单ID")
    private String menuId;


    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "状态")
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

    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String MENU_ID = "menu_id";


}
