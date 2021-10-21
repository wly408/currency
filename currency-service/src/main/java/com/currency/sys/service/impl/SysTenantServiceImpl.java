package com.currency.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.currency.common.mybatis.BaseServiceImpl;
import com.currency.dto.sys.QuerySysTenantDTO;
import com.currency.dto.sys.SysTenantDTO;
import com.currency.sys.entity.SysTenant;
import com.currency.sys.mapper.SysTenantMapper;
import com.currency.sys.service.ISysTenantService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ObjectUtil;
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
public class SysTenantServiceImpl extends BaseServiceImpl<SysTenantMapper, SysTenant, SysTenantDTO> implements ISysTenantService {
    @Override
    public String addTenant(SysTenantDTO sysTenantDTO) {
        SysTenant sysTenant = ObjectUtil.copy(sysTenantDTO, SysTenant.class);
        sysTenant.setTenantId(UUIDUtils.getUUID());
        this.save(sysTenant);
        return sysTenant.getTenantId();
    }

    @Override
    public IPage<SysTenantDTO> list(QuerySysTenantDTO querySysTenantDTO) {
        LoginContextUtil.dealQuery(querySysTenantDTO);
        Page page = new Page(querySysTenantDTO.getCurrent(), querySysTenantDTO.getPageSize());
        IPage<SysTenantDTO> pageInfo = this.baseMapper.list(page, querySysTenantDTO);

        return pageInfo;
    }

    @Override
    public void delTenantByTenantId(String tenantId) {
        this.delHadById(tenantId);

    }

    @Override
    public SysTenantDTO getTenantByTenantId(String tenantId) {
        return this.getDtoById(tenantId, SysTenantDTO.class);
    }
}
