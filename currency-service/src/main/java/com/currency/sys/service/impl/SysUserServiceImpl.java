package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.constrants.CommonConstrants;
import com.currency.dto.sys.QuerySysUserDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.exception.BusinessException;
import com.currency.sys.entity.SysUser;
import com.currency.sys.mapper.SysUserMapper;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ObjectUtil;
import com.currency.utils.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser,SysUserDTO> implements ISysUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String addUser(SysUserDTO sysUserDTO) {
        String tenantId = sysUserDTO.getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            tenantId = LoginContextUtil.getTenantId();
        }
        LoginContextUtil.dealAddAndEdit(tenantId);
        this.check(null, sysUserDTO.getUserCode(), sysUserDTO.getUserType(), tenantId);
        SysUser user = ObjectUtil.copy(sysUserDTO, SysUser.class);
        user.setPassword(bCryptPasswordEncoder.encode(sysUserDTO.getPassword()));
        user.setUserId(UUIDUtils.getUUID());
        this.save(user);
        return user.getUserId();
    }

    private void check(String userId, String userCode, String userType, String tenantId) {
        SysUserDTO sysUserDTO = this.getSysUserByUserCodeAndUserType(userCode, userType, tenantId);

        if (StringUtils.isEmpty(userId) && sysUserDTO != null) {
            throw new BusinessException("账号：" + userCode + "已经存在");
        }
        if (StringUtils.isNotEmpty(userId)) {
            if (sysUserDTO != null&&!sysUserDTO.getUserId().equals(userId)  ) {
                throw new BusinessException("账号：" + userCode + "已经存在");
            }
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
        return this.getDtoById(userId,SysUserDTO.class);
    }

    @Override
    public IPage<SysUserDTO> list(QuerySysUserDTO querySysUserDTO) {
        LoginContextUtil.dealQuery(querySysUserDTO);
        //设置分页信息
        Page page = new Page(querySysUserDTO.getCurrent(), querySysUserDTO.getPageSize());
        IPage pageInfo = this.baseMapper.list(page, querySysUserDTO);
        return pageInfo;
    }

    @Override
    public void delUserByUserId(String userId) {
        this.delHadById(userId);
    }

}
