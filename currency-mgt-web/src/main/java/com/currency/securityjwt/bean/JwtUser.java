package com.currency.securityjwt.bean;

import com.currency.dto.sys.SysRoleDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.enums.CommonEnum;
import com.currency.sys.service.ISysRoleService;
import com.currency.utils.SpringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {

    private String userId;
    private String userCode;
    private String password;
    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public JwtUser() {

    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(SysUserDTO user) {
        userId = user.getUserId();
        userCode = user.getUserCode();
        password = user.getPassword();
        enabled = true;
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        List<String> roleList = getUserRole(userId);
        //新增游客角色
        list.add(new SimpleGrantedAuthority(CommonEnum.SYS_ROLE_GUEST.getValue()));
        if (!CollectionUtils.isEmpty(roleList)) {
            roleList.forEach(role -> list.add(new SimpleGrantedAuthority(role)));

        }
        authorities = list;
    }

    public static List<String> getUserRole(String userId) {
        List<String> roleList = new ArrayList<>();
        ISysRoleService sysRoleService = SpringUtil.getBean(ISysRoleService.class);
        List<SysRoleDTO> roleDtoList = sysRoleService.getRoleListByUserId(userId);
        roleList.add(CommonEnum.SYS_ROLE_GUEST.getValue());
        if (!CollectionUtils.isEmpty(roleList)) {
            roleDtoList.forEach(role -> roleList.add(role.getRoleCode()));

        }

        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
