package com.currency.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.dto.sys.SysUserDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface ISysMenuService  {
    /**
     * 根据用户ID获取菜单列表
     * @param userId
     * @return
     */
    List<SysMenuDTO> getUserSysMenuByUserId(String userId);

    SysMenuDTO getSysMenuByMenuId(String menuId);

    IPage<SysUserDTO> list(QuerySysMenuDTO querySysMenuDTO);

    String addMenu(SysMenuDTO sysMenuDTO);

    void edit(SysMenuDTO sysMenuDTO);

    void delByMenuId(String menuId);
}
