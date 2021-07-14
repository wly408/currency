package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysRoleDTO;
import com.currency.dto.sys.SysRoleDTO;
import com.currency.sys.service.ISysRoleService;
import com.currency.utils.ResultHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/sys/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/getRole")
    @ApiOperation("根据ID获取角色")
    public ResultHandler<SysRoleDTO> getRole(@RequestParam(value = "roleId") String roleId) {
        try {
            SysRoleDTO sysRoleDTO = sysRoleService.getRoleByRoleId(roleId);
            return ResultHandler.suc(sysRoleDTO);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation("查询角色列表")
    public ResultHandler<IPage<SysRoleDTO>> list(@RequestBody QuerySysRoleDTO querySysRoleDTO) {
        try {
            IPage<SysRoleDTO> page = sysRoleService.list(querySysRoleDTO);
            return ResultHandler.suc(page);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation("新增角色")
    public ResultHandler add(@RequestBody @Valid SysRoleDTO sysRoleDTO) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/edit")
    @ApiOperation("编辑角色")
    public ResultHandler edit(@RequestBody @Valid SysRoleDTO sysRoleDTO) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @DeleteMapping("/del")
    @ApiOperation("删除角色")
    public ResultHandler del(@RequestParam(value = "roleId") String roleId) {
        try {
            sysRoleService.delByRoleId(roleId);
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }
}
