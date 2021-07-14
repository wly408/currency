package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.sys.entity.SysMenu;
import com.currency.sys.mapper.SysMenuMapper;
import com.currency.sys.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu, SysMenuDTO> implements ISysMenuService {

    @Override
    public List<SysMenuDTO> getUserSysMenuByUserId(String userId) {

        return this.baseMapper.getUserSysMenuByUserId(userId);
    }

    @Override
    public SysMenuDTO getSysMenuByMenuId(String menuId) {
        return this.getDtoById(menuId, SysMenuDTO.class);
    }

    @Override
    public IPage<SysUserDTO> list(QuerySysMenuDTO querySysMenuDTO) {
        return null;
    }

    @Override
    public String addMenu(SysMenuDTO sysMenuDTO) {
        return null;
    }

    @Override
    public void edit(SysMenuDTO sysMenuDTO) {

    }

    @Override
    public void delByMenuId(String menuId) {
        this.delHadById(menuId);
    }
}
