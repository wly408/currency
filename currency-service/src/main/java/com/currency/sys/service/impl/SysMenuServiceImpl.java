package com.currency.sys.service.impl;

import com.currency.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Override
    public void test() {
        System.out.println("hello world");
    }
}
