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
 * 用户表
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser extends BaseEntity {

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
    public static final String USER_ID = "user_id";

    public static final String USER_CODE = "user_code";

    public static final String USER_NAME = "user_name";

    public static final String USER_PHOTO = "user_photo";

    public static final String REALNAME = "realname";

    public static final String EMAIL = "email";

    public static final String ID_NUM = "id_num";

    public static final String ID_TYPE = "id_type";

    public static final String PHONE = "phone";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String USER_TYPE = "user_type";


}
