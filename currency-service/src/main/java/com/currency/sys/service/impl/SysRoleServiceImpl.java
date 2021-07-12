package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.currency.dto.sys.SysRoleDTO;
import com.currency.sys.entity.SysRole;
import com.currency.sys.mapper.SysRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService{

    @Override
    public List<SysRoleDTO> getRoleListByUserId(String userId) {
        return this.baseMapper.getRoleListByUserId(userId);
    }
}
