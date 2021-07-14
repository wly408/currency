package com.currency.sys.service.impl;

import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.dto.sys.SysUserDTO;
import com.currency.sys.entity.SysRoleMenu;
import com.currency.sys.mapper.SysRoleMenuMapper;
import com.currency.sys.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu, SysUserDTO> implements ISysRoleMenuService{

}
