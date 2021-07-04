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
 * 租户表
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysTenant对象", description="租户表")
public class SysTenant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户ID")
    @TableId(value = "tenant_id", type = IdType.INPUT)
    private String tenantId;

    @ApiModelProperty(value = "租户编码")
    private String tenantCode;

    @ApiModelProperty(value = "租户名称")
    private String tenantName;


    public static final String TENANT_ID = "tenant_id";

    public static final String TENANT_CODE = "tenant_code";

    public static final String TENANT_NAME = "tenant_name";

}
