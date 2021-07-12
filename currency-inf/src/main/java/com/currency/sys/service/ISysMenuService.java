package com.currency.sys.service;

import com.currency.dto.sys.SysMenuDTO;

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
}
