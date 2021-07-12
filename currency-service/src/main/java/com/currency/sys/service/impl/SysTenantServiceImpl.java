package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.currency.dto.sys.SysTenantDTO;
import com.currency.sys.entity.SysTenant;
import com.currency.sys.mapper.SysTenantMapper;
import com.currency.sys.service.ISysTenantService;
import com.currency.utils.BaseResult;
import com.currency.utils.ObjectUtil;
import com.currency.utils.ResultUtil;
import com.currency.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 租户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService {
    @Override
    public BaseResult addSysTenant(SysTenantDTO sysTenantDTO) {

        SysTenant sysTenant =ObjectUtil.copy(sysTenantDTO,SysTenant.class);
        sysTenant.setTenantId(UUIDUtils.getUUID());
        this.save(sysTenant);
        return ResultUtil.suc();
    }
}
