package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.constrants.CommonConstrants;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.exception.BusinessException;
import com.currency.sys.entity.SysMenu;
import com.currency.sys.mapper.SysMenuMapper;
import com.currency.sys.service.ISysMenuService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ObjectUtil;
import com.currency.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)

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
        LoginContextUtil.dealQuery(querySysMenuDTO);
        Page page = new Page(querySysMenuDTO.getCurrent(), querySysMenuDTO.getPageSize());
        IPage<SysUserDTO> pageInfo = this.baseMapper.list(page, querySysMenuDTO);
        return pageInfo;
    }

    @Override
    public String addMenu(SysMenuDTO sysMenuDTO) {
        String tenantId = sysMenuDTO.getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            tenantId = LoginContextUtil.getTenantId();
        }
        sysMenuDTO.setTenantId(tenantId);
        LoginContextUtil.dealAddAndEdit(tenantId);

        this.check(null, sysMenuDTO.getMenuSn(), tenantId);
        SysMenu sysMenu = ObjectUtil.copy(sysMenuDTO, SysMenu.class);
        sysMenu.setMenuId(UUIDUtils.getUUID());
        this.save(sysMenu);
        return sysMenu.getMenuId();
    }

    private void check(String menuId, String menuSn, String tenantId) {
        SysMenuDTO data = this.getSysMenuByMenuSnAndTenantId(menuSn, tenantId);

        if (StringUtils.isEmpty(menuId) && data != null) {
            throw new BusinessException("账号：" + tenantId + "已经存在");
        }
        if (StringUtils.isNotEmpty(menuId)) {
            if (data != null && !data.getMenuId().equals(menuId)) {
                throw new BusinessException("账号：" + menuSn + "已经存在");
            }
        }

    }

    @Override
    public void edit(SysMenuDTO sysMenuDTO) {
        SysMenu sysMenu = this.getById(sysMenuDTO.getMenuId());
        if (sysMenu != null) {
            LoginContextUtil.dealAddAndEdit(sysMenu.getTenantId());
            sysMenu.setMenuSn(sysMenuDTO.getMenuSn());
            sysMenu.setMenuIcon(sysMenuDTO.getMenuIcon());
            sysMenu.setMenuName(sysMenuDTO.getMenuName());
            sysMenu.setMenuType(sysMenuDTO.getMenuType());
            sysMenu.setMenuUrl(sysMenuDTO.getMenuUrl());
            sysMenu.setRemark(sysMenuDTO.getRemark());
            sysMenu.setMenuSort(sysMenuDTO.getMenuSort());
            this.save(sysMenu);
        }
    }

    @Override
    public void delByMenuId(String menuId) {
        this.delHadById(menuId);
    }

    @Override
    public SysMenuDTO getSysMenuByMenuSnAndTenantId(String menuSn, String tenantId) {
        Wrapper<SysMenu> query = new QueryWrapper<>();
        SysMenu queryMenu = new SysMenu();
        queryMenu.setMenuSn(menuSn);
        queryMenu.setTenantId(tenantId);
        queryMenu.setStatusCd(CommonConstrants.COMMON_YES);
        ((QueryWrapper<SysMenu>) query).setEntity(queryMenu);

        List<SysMenu> list = this.baseMapper.selectList(query);
        if (!CollectionUtils.isEmpty(list)) {
            return ObjectUtil.copy(list.get(0), SysMenuDTO.class);
        }
        return null;
    }
}
