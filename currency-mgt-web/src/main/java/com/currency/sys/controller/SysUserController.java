package com.currency.sys.controller;


import com.currency.enums.CommonEnum;
import com.currency.sys.dto.SysUserDTO;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public BaseResult register(@RequestBody @Valid SysUserDTO userDTO){
        userDTO.setUserType(CommonEnum.SYS_USER_TYPE_ADMIN.getValue());
        return sysUserService.addUser(userDTO);

    }
}
