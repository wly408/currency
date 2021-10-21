package com.currency.config;

import com.currency.securityjwt.config.SecurityJwtConfig;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
@Primary
@Getter
public class MgtSecurityJwtConfig extends SecurityJwtConfig {
    private String roleClaims = "rol";
    private long verifyCodeExpiration = 5 * 60L;
    private String verifyCodeUid = "verifyCodeUid";
    private String jwtSecretKey = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";
    private String tokenHeader = "Authorization";
    private String tokenPrefix = "Bearer ";
    private long expiration = 60 * 60L;


    private  String[] whiteList = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            //knife4j
            "/doc.html",

            //登录
            "/auth/login",

            //验证码
            "/auth/createCode",
            "/auth/createCode",
            "/test/**"

    };
    private  String[] blackList = {
            "/sys/user/register"
    };

}
