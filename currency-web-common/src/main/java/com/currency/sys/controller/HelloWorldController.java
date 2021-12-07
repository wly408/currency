package com.currency.sys.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloWorld")
public class HelloWorldController {

    @GetMapping("")
    @ApiOperation("获取当前用户的菜单列表")
    public String helloWorld() {
        return "helloWorld";
    }
}
