package com.currency.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public final class LoginContext implements Serializable {
    private String userId;
    private String userType;
    private String userCode;
    private String userName;
    private String tenantId;
}
