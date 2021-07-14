package com.currency.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysUserDTO;
import com.currency.dto.sys.SysUserDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface ISysUserService  {

    String addUser(SysUserDTO sysUserDTO);

    SysUserDTO getSysUserByUserCodeAndUserType(String userCode,String userType,String tenantId);

    SysUserDTO getSysUserByUserId(String userId);

    IPage<SysUserDTO> list(QuerySysUserDTO querySysUserDTO);

    void delUserByUserId(String userId);

}
