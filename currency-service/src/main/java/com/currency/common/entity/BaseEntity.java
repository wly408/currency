package com.currency.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 吴良勇
 * @date 2019/12/19 15:04
 */
@Data
public abstract class BaseEntity implements Serializable {

    @ApiModelProperty(value = "租户ID")
    @TableField(fill = FieldFill.INSERT)
    private String tenantId;

    @ApiModelProperty(value = " 状态")
    @TableField(fill = FieldFill.INSERT)
    private String statusCd;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    public static final String TENANT_ID = "tenant_id";

    public static final String STATUS_CD = "status_cd";

    public static final String CREATE_USER = "create_user";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String REMARK = "remark";

}
