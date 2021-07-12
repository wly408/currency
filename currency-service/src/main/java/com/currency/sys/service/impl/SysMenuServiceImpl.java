package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.sys.entity.SysMenu;
import com.currency.sys.mapper.SysMenuMapper;
import com.currency.sys.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenuDTO> getUserSysMenuByUserId(String userId) {
        return this.baseMapper.getUserSysMenuByUserId(userId);
    }
}
