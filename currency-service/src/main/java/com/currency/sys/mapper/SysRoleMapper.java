package com.currency.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.currency.dto.sys.SysRoleDTO;
import com.currency.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleDTO> getRoleListByUserId(@Param("userId") String userId);
}
