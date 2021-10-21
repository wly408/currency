package com.currency.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysRoleDTO;
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

    /**
     * 根据ID获取角色
     * @param roleId
     * @return
     */
    SysRoleDTO getRoleByRoleId(String roleId);

    /**
     * 删除角色
     * @param roleId
     */
    void delByRoleId(String roleId);

    /**
     * 分页查询角色
     * @param querySysRoleDTO
     * @return
     */
    IPage<SysRoleDTO> list(QuerySysRoleDTO querySysRoleDTO);

    void testCommit();

}
