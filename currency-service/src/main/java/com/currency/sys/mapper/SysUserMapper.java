package com.currency.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.currency.dto.sys.QuerySysUserDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    IPage<SysUserDTO> list(IPage<SysUserDTO> page, @Param(Constants.WRAPPER) Wrapper<QuerySysUserDTO>  wrapper);


}
