package com.currency.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysTenantDTO;
import com.currency.dto.sys.SysTenantDTO;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface ISysTenantService {


    String addTenant(SysTenantDTO sysTenantDTO);


    IPage<SysTenantDTO> list(QuerySysTenantDTO querySysTenantDTO);

    /**
     * 删除租户
     * @param tenantId
     */
    void delTenantByTenantId(String tenantId);


    SysTenantDTO getTenantByTenantId(String tenantId);
}
