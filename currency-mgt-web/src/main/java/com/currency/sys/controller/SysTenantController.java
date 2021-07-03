package com.currency.sys.controller;


import com.currency.sys.service.ISysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/sys/sys-tenant")
public class SysTenantController {

    @Autowired
    private ISysTenantService sysTenantService;


    @GetMapping("test")
    public String test(){
//        sysTenantService.add();
        return "测试成功";
    }
}
