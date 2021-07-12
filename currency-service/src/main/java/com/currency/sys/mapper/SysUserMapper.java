package com.currency.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.sys.dto.QuerySysUserDTO;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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


    IPage<SysUserDTO> list(IPage<SysUserDTO> page, @Param("query") QuerySysUserDTO query);


}
