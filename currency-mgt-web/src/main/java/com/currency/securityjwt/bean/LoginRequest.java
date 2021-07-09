package com.currency.securityjwt.bean;

import lombok.Data;

/**
 * 登录请求对象
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
    private String tenantId;
}
