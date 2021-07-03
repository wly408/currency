package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.currency.sys.entity.SysUser;
import com.currency.sys.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IService<SysUser> {

}
