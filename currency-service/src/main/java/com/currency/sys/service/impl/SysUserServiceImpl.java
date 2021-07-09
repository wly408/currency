package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.constrants.CommonConstrants;
import com.currency.exception.BusinessException;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.entity.SysUser;
import com.currency.sys.mapper.SysUserMapper;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public BaseResult addUser(SysUserDTO sysUserDTO) {
        String tenantId = sysUserDTO.getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            tenantId = LoginContextUtil.getTenantId();
        }
        this.check(null, sysUserDTO.getUserCode(), sysUserDTO.getUserType(), tenantId);
        SysUser user = ObjectUtil.copy(sysUserDTO, SysUser.class);
        user.setPassword(bCryptPasswordEncoder.encode(sysUserDTO.getPassword()));
        user.setUserId(UUIDUtils.getUUID());
        this.save(user);
        return ResultUtil.suc();
    }

    private void check(String userId, String userCode, String userType, String tenantId) {
        SysUserDTO sysUserDTO = this.getSysUserByUserCodeAndUserType(userCode, userType, tenantId);
        if (StringUtils.isEmpty(userId)) {
            throw new BusinessException("账号：" + userCode + "已经存在");
        }
        if (!userId.equals(sysUserDTO.getUserId())) {
            throw new BusinessException("账号：" + userCode + "已经存在");
        }

    }

    @Override
    public SysUserDTO getSysUserByUserCodeAndUserType(String userCode, String userType, String tenantId) {
        Wrapper<SysUser> query = new QueryWrapper<>();
        SysUser queryUser = new SysUser();
        queryUser.setUserCode(userCode);
        queryUser.setUserType(userType);
        queryUser.setTenantId(tenantId);
        queryUser.setStatusCd(CommonConstrants.COMMON_YES);
        ((QueryWrapper<SysUser>) query).setEntity(queryUser);

        List<SysUser> list = this.baseMapper.selectList(query);
        if (!CollectionUtils.isEmpty(list)) {
            return ObjectUtil.copy(list.get(0), SysUserDTO.class);
        }
        return null;
    }

    @Override
    public SysUserDTO getSysUserByUserId(String userId) {
        SysUser sysUser = this.getById(userId);
        if (CommonConstrants.COMMON_YES.equals(sysUser.getStatusCd())) {
            return ObjectUtil.copy(sysUser, SysUserDTO.class);
        }

        return null;
    }

}
