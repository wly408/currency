package com.currency.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "role_id", type = IdType.INPUT)
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

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


    public static final String ROLE_ID = "role_id";

    public static final String ROLE_NAME = "role_name";

    public static final String ROLE_CODE = "role_code";

    public static final String TENANT_ID = "tenant_id";

    public static final String STATUS_CD = "status_cd";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String REMARK = "remark";

}
