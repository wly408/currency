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
@ApiModel(value="SysUserRole对象", description="")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

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


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String USER_ID = "user_id";

    public static final String TENANT_ID = "tenant_id";

    public static final String STATUS_CD = "status_cd";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String REMARK = "remark";

}
