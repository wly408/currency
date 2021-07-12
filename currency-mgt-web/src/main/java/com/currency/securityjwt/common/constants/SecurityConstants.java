package com.currency.securityjwt.common.constants;

/**
 * @author wly
 * @description Spring Security相关配置常量
 */
public final class SecurityConstants {

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "rol";

    /**
     * 验证码过期时间
     */
    public static final long  VERIFY_CODE_EXPIRATION = 5*60L;

    public static final String  VERIFY_CODE_UID = "verifyCodeUid";




    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;


    /**
     * JWT签名密钥硬编码到应用程序代码中，应该存放在环境变量或.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    // Swagger WHITELIST
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            //knife4j
            "/doc.html",
    };

    public static final String H2_CONSOLE = "/h2-console/**";

    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            //登录
            "/auth/login",
            //注册
            "/sys/user/register",
            //验证码
            "/auth/createCode"
    };

    private SecurityConstants() {
    }

}
