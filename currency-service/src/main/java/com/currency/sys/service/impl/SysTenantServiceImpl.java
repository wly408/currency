package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.sys.entity.SysTenant;
import com.currency.sys.mapper.SysTenantMapper;
import com.currency.sys.service.ISysTenantService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService {

}
