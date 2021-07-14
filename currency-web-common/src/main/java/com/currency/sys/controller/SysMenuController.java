package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.sys.service.ISysMenuService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ResultHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/sys/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping("/getCurrentUserSysMenuList")
    @ApiOperation("获取当前用户的菜单列表")

    public ResultHandler<List<SysMenuDTO>> getCurrentUserSysMenuList() {
        try {
            List<SysMenuDTO> list = sysMenuService.getUserSysMenuByUserId(LoginContextUtil.getUserId());
            return ResultHandler.suc(list);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }

    }
    @GetMapping("/getMenu")
    @ApiOperation("根据ID获取菜单")
    public ResultHandler<SysMenuDTO> getMenu(@RequestParam(value = "menuId") String menuId) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResultHandler<IPage> list(@RequestBody QuerySysMenuDTO querySysMenuDTO) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation("新增角色")
    public ResultHandler add(@RequestBody @Valid SysMenuDTO sysMenuDTO) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/edit")
    @ApiOperation("编辑角色")
    public ResultHandler edit(@RequestBody @Valid SysMenuDTO sysMenuDTO) {
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
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

}
