package com.currency.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.currency.dto.sys.QuerySysUserDTO;
import com.currency.dto.sys.SysUserDTO;
import com.currency.enums.CommonEnum;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.BaseResult;
import com.currency.utils.ResultUtil;
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
    public BaseResult register(@RequestBody @Valid SysUserDTO userDTO) {
        try {
            userDTO.setUserType(CommonEnum.SYS_USER_TYPE_ADMIN.getValue());
            return sysUserService.addUser(userDTO);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
    }

    @GetMapping("/getUser/{userId}")
    @ApiOperation("根据ID获取用户")
    public BaseResult<SysUserDTO> register(@PathVariable(value = "userId") String userId) {
        try {
            SysUserDTO userDTO = sysUserService.getSysUserByUserId(userId);
            return ResultUtil.suc(userDTO);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
    }

    @GetMapping("/list")
    @ApiOperation("根据ID获取用户")
    public BaseResult<IPage> list(@RequestBody @Valid QuerySysUserDTO querySysUserDTO) {
        try {
            IPage<SysUserDTO> page = sysUserService.list(querySysUserDTO);
            return ResultUtil.suc(page);
        } catch (Exception e) {
            return ResultUtil.error(e);
        }
    }


}
