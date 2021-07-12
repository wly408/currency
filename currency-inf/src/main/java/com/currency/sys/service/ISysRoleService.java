package com.currency.sys.service;

import com.currency.dto.sys.SysRoleDTO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface ISysRoleService  {
    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    List<SysRoleDTO> getRoleListByUserId(String userId);
}
