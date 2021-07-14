package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.constrants.CommonConstrants;
import com.currency.dto.sys.QuerySysRoleDTO;
import com.currency.dto.sys.SysRoleDTO;
import com.currency.enums.CommonEnum;
import com.currency.exception.BusinessException;
import com.currency.sys.entity.SysRole;
import com.currency.sys.mapper.SysRoleMapper;
import com.currency.sys.service.ISysRoleService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ObjectUtil;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRoleDTO> getRoleListByUserId(String userId) {
        return this.baseMapper.getRoleListByUserId(userId);
    }

    @Override
    public SysRoleDTO getRoleByRoleId(String roleId) {
        SysRole sysRole = this.getById(roleId);
        if (sysRole != null && CommonConstrants.COMMON_YES.equals(sysRole.getStatusCd())) {
            return ObjectUtil.copy(sysRole, SysRoleDTO.class);
        }
        return null;
    }

    @Override
    public void delByRoleId(String roleId) {
        SysRole sysRole = this.getById(roleId);
        if (sysRole == null || CommonConstrants.COMMON_NO.equals(sysRole.getStatusCd())) {
            return;
        }
        if (CommonEnum.SYS_ROLE_SUPER_ADMIN.getValue().equals(sysRole.getRoleCode())) {
            throw new BusinessException("该角色为内置角色不可删除");

        }
        LoginContextUtil.hasDelAuthority(sysRole);
        this.save(sysRole);
    }

    @Override
    public IPage<SysRoleDTO> list(QuerySysRoleDTO querySysRoleDTO) {


        return null;
    }
}
