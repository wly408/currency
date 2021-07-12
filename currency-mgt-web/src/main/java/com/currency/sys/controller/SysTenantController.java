package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysTenantDTO;
import com.currency.dto.sys.SysTenantDTO;
import com.currency.sys.service.ISysTenantService;
import com.currency.utils.ResultHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/sys/sysTenant")
public class SysTenantController {

    @Autowired
    private ISysTenantService sysTenantService;


    @GetMapping("/list")
    @ApiOperation("租户列表查询")
    public ResultHandler<IPage<SysTenantDTO>> list(@RequestBody QuerySysTenantDTO querySysTenantDTO) {
        try {
            IPage<SysTenantDTO> page = sysTenantService.list(querySysTenantDTO);
            return ResultHandler.suc(page);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }

    }

    @PostMapping("/add")
    @ApiOperation("新增租户")
    public ResultHandler<String> add(@RequestBody @Valid SysTenantDTO tenantDTO) {
        try {
            String id = sysTenantService.addTenant(tenantDTO);
            return ResultHandler.suc(id);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/update")
    @ApiOperation("修改租户")
    public ResultHandler update(@RequestBody @Valid SysTenantDTO tenantDTO) {
        try {
            return null;
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/del")
    @ApiOperation("删除租户")
    public ResultHandler del(@RequestParam(value = "tenantId") String tenantId) {
        try {
            return null;
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }
}
