package com.currency.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 吴良勇
 * @date 2019/12/19 15:04
 */
@Data
public abstract class BaseEntity implements Serializable {

    private String createUser;
    private Date createDate;
    private String statusCd;
    private String updateUser;
    private Date updateDate;
    private String tenantId;
}
