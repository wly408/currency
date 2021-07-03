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
 * 用户表
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.INPUT)
    private String userId;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户头像")
    private String userPhoto;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "所属租户")
    private String tenantId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "证件号码")
    private String idNum;

    @ApiModelProperty(value = "证件类型")
    private String idType;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "用户类型：0：通用1：管理员2：用户")
    private String userType;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "状态")
    private String statusCd;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注")
    private String remark;


    public static final String USER_ID = "user_id";

    public static final String USER_CODE = "user_code";

    public static final String USER_NAME = "user_name";

    public static final String USER_PHOTO = "user_photo";

    public static final String REALNAME = "realname";

    public static final String TENANT_ID = "tenant_id";

    public static final String EMAIL = "email";

    public static final String ID_NUM = "id_num";

    public static final String ID_TYPE = "id_type";

    public static final String PHONE = "phone";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String USER_TYPE = "user_type";

    public static final String CREATE_DATE = "create_date";

    public static final String STATUS_CD = "status_cd";

    public static final String CREATE_USER = "create_user";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_DATE = "update_date";

    public static final String REMARK = "remark";

}
