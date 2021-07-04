package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.entity.SysUser;
import com.currency.sys.mapper.SysUserMapper;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.BaseResult;
import com.currency.utils.ObjectUtil;
import com.currency.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public BaseResult addUser(SysUserDTO sysUserDTO) {
        SysUser user = ObjectUtil.copy(sysUserDTO,SysUser.class);
        this.save(user);
        return ResultUtil.suc();
    }
}
