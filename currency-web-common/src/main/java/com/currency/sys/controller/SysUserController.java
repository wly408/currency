package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysUserDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.ResultHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResultHandler<String> register(@RequestBody @Valid SysUserDTO userDTO) {
        try {
            String userId = sysUserService.addUser(userDTO);
            return ResultHandler.suc(userId);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @GetMapping("/getUser")
    @ApiOperation("根据ID获取用户")
    public ResultHandler<SysUserDTO> getUser(@RequestParam(value = "userId") String userId) {
        try {
            SysUserDTO userDTO = sysUserService.getSysUserByUserId(userId);
            return ResultHandler.suc(userDTO);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation("查询用户列表")
    public ResultHandler<IPage> list(@RequestBody QuerySysUserDTO querySysUserDTO) {
        try {
            IPage<SysUserDTO> page = sysUserService.list(querySysUserDTO);
            return ResultHandler.suc(page);
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

    @PostMapping("/edit")
    @ApiOperation("用户编辑")
    public ResultHandler edit(@RequestBody @Valid SysUserDTO userDTO) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }
    @DeleteMapping("/del")
    @ApiOperation("用户删除")
    public ResultHandler del(@RequestParam(value = "userId") String userId) {
        try {
            return ResultHandler.suc();
        } catch (Exception e) {
            return ResultHandler.error(e);
        }
    }

}
