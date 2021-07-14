package com.currency.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.dto.sys.QuerySysTenantDTO;
import com.currency.dto.sys.SysTenantDTO;
import com.currency.sys.entity.SysTenant;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 租户表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface SysTenantMapper extends BaseMapper<SysTenant> {

    IPage<SysTenantDTO> list(Page page, @Param("query") QuerySysTenantDTO querySysTenantDTO);
}
