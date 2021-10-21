package com.currency.securityjwt.bean;

import com.currency.dto.sys.SysMenuDTO;
import lombok.Data;

import java.util.List;

/**
 * 登录请求对象
 */
@Data
public class LoginResponse {
    private String token;
    List<SysMenuDTO> menuList;
}
