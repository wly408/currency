package com.currency.sys.controller;


import com.currency.dto.sys.SysMenuDTO;
import com.currency.sys.service.ISysMenuService;
import com.currency.utils.ResultHandler;
import com.currency.utils.LoginContextUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
