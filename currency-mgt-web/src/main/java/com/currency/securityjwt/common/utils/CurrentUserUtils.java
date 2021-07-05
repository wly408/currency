package com.currency.securityjwt.common.utils;

import com.currency.enums.CommonEnum;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @description 获取当前请求的用户
 */
@Component
public class CurrentUserUtils {

    @Autowired
    private ISysUserService sysUserService;

    public SysUserDTO getCurrentUser() {
        return sysUserService.getSysUserByUserCodeAndUserType(getCurrentUserName(), CommonEnum.SYS_USER_TYPE_ADMIN.getValue());
    }

    private  String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
