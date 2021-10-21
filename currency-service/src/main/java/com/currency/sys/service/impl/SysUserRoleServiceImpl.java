package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.dto.sys.SysUserRoleDTO;
import com.currency.sys.entity.SysUserRole;
import com.currency.sys.mapper.SysUserRoleMapper;
import com.currency.sys.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole, SysUserRoleDTO> implements ISysUserRoleService {


}
