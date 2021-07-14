package com.currency.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenuDTO> getUserSysMenuByUserId(@Param("userId") String userId);

    IPage<SysUserDTO> list(Page page, @Param("query")QuerySysMenuDTO querySysMenuDTO);
}
