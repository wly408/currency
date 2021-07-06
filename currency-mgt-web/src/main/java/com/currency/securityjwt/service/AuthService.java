package com.currency.securityjwt.service;

import com.currency.enums.CommonEnum;
import com.currency.securityjwt.bean.JwtUser;
import com.currency.securityjwt.bean.LoginRequest;
import com.currency.securityjwt.common.utils.CurrentUserUtils;
import com.currency.securityjwt.common.utils.JwtTokenUtils;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shuang.kou
 **/
@Service
public class AuthService {
    @Autowired
    private  ISysUserService sysUserService;
    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private  CurrentUserUtils currentUserUtils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String createToken(LoginRequest loginRequest) {
        SysUserDTO user = sysUserService.getSysUserByUserCodeAndUserType(loginRequest.getUsername(), CommonEnum.SYS_USER_TYPE_ADMIN.getValue());
        if (!this.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("账号或者密码错误");
        }
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getUserId(), authorities, loginRequest.getRememberMe());
        stringRedisTemplate.opsForValue().set(user.getUserId(), token);
        return token;
    }

    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getUserId());
    }

    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
