package com.currency.securityjwt.service;

import com.currency.constrants.CommonConstrants;
import com.currency.dto.sys.SysTenantDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.enums.CommonEnum;
import com.currency.securityjwt.bean.JwtUser;
import com.currency.securityjwt.bean.LoginRequest;
import com.currency.securityjwt.config.SecurityJwtConfig;
import com.currency.securityjwt.common.utils.JwtTokenUtils;
import com.currency.sys.service.ISysTenantService;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ISysTenantService sysTenantService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String createToken(LoginRequest loginRequest) {
        String verifyCodeUid = loginRequest.getVerifyCodeUid();
        String verifyCode = loginRequest.getVerifyCode();
        if (StringUtils.isEmpty(verifyCodeUid) || StringUtils.isEmpty(verifyCode)) {
            throw new BadCredentialsException("验证码为空");
        }
        String tenantId = SpringUtil.getProperty(CommonConstrants.SPRING_CONFIG_KEY_SYS_TENANT_ID);
        if (StringUtils.isEmpty(tenantId)) {
            tenantId = loginRequest.getTenantId();
        }
        if (StringUtils.isEmpty(tenantId)) {
            throw new BadCredentialsException("租户ID为空");
        }
        SysTenantDTO sysTenantDTO = sysTenantService.getTenantByTenantId(tenantId);
        if (sysTenantDTO == null) {
            throw new BadCredentialsException("非法用户");
        }
        loginRequest.setTenantId(tenantId);
        //校验验证码
        String realVerifyCode = stringRedisTemplate.opsForValue().get(verifyCodeUid);
        if (!verifyCode.equalsIgnoreCase(realVerifyCode)) {
            throw new BadCredentialsException("验证码错误");
        }
        stringRedisTemplate.delete(verifyCodeUid);
        SysUserDTO user = sysUserService.getSysUserByUserCodeAndUserType(loginRequest.getUsername(), CommonEnum.SYS_USER_TYPE_ADMIN.getValue(), loginRequest.getTenantId());
        if (user == null) {
            throw new BadCredentialsException("账号或者密码错误");
        }
        if (!this.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("账号或者密码错误");
        }
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("用户状态异常");
        }

        List<String> authorities = new ArrayList<>();
        Collection<? extends GrantedAuthority> authoritiesTemp = jwtUser.getAuthorities();
        if (!CollectionUtils.isEmpty(authoritiesTemp)) {
            authorities = authoritiesTemp
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

        String token = JwtTokenUtils.createToken(user.getUserCode(), user.getUserId(), authorities);
        //token一小时有效
        stringRedisTemplate.opsForValue().set(user.getUserId(), token, SecurityJwtConfig.getInstance().getExpiration(), TimeUnit.SECONDS);
        return token;
    }

    public void removeToken() {
        stringRedisTemplate.delete(LoginContextUtil.getUserId());
    }

    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
