package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysMenuDTO;
import com.currency.dto.sys.SysMenuDTO;
import com.currency.dto.sys.SysUserDTO;
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
            SysMenuDTO sysMenuDTO = sysMenuService.getSysMenuByMenuId(menuId);
            return ResultHandler.suc(sysMenuDTO);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation("查询菜单列表")
    public ResultHandler<IPage> list(@RequestBody QuerySysMenuDTO querySysMenuDTO) {
        try {
            IPage<SysUserDTO> page = sysMenuService.list(querySysMenuDTO);
            return ResultHandler.suc(page);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/add")
    @ApiOperation("新增菜单")
    public ResultHandler add(@RequestBody @Valid SysMenuDTO sysMenuDTO) {
        try {
            String menuId = sysMenuService.addMenu(sysMenuDTO);
            return ResultHandler.suc(menuId);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/edit")
    @ApiOperation("编辑角色")
    public ResultHandler edit(@RequestBody @Valid SysMenuDTO sysMenuDTO) {
        try {
            sysMenuService.edit(sysMenuDTO);
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @DeleteMapping("/del")
    @ApiOperation("删除菜单")
    public ResultHandler del(@RequestParam(value = "menuId") String menuId) {
        try {
            sysMenuService.delByMenuId(menuId);
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

}
