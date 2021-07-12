package com.currency.sys.service;

import com.currency.dto.sys.SysTenantDTO;
import com.currency.utils.BaseResult;

/**
 * <p>
 * 租户表 服务类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
public interface ISysTenantService {


    BaseResult addSysTenant(SysTenantDTO sysTenantDTO);



}
