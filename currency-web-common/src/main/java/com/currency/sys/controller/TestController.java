package com.currency.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysRoleDTO;
import com.currency.dto.sys.SysRoleDTO;
import com.currency.sys.service.ISysRoleService;
import com.currency.utils.ResultHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")

public class TestController {
    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/list")
    @ApiOperation("本地SQL查询")
    public ResultHandler<IPage<SysRoleDTO>> sqlList(@RequestBody QuerySysRoleDTO querySysRoleDTO) {
        try {
            IPage<SysRoleDTO> page = sysRoleService.list(querySysRoleDTO);
            return ResultHandler.suc(page);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }
    @GetMapping("/commit")
    @ApiOperation("本地SQL事务")
    public ResultHandler testCommit() {
        try {
           sysRoleService.testCommit();
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }
}
