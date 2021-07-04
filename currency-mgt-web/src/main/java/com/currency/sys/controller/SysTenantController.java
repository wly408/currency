package com.currency.sys.controller;


import com.currency.sys.dto.SysTenantDTO;
import com.currency.sys.service.ISysTenantService;
import com.currency.utils.BaseResult;
import com.currency.utils.ResultUtil;
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
@RequestMapping("/sys/sysTenant")
public class SysTenantController {

    @Autowired
    private ISysTenantService sysTenantService;


    @GetMapping("test")
    public BaseResult test(){
        try{
            SysTenantDTO sysTenantDTO = new SysTenantDTO();
            sysTenantDTO.setStatusCd("1");
            sysTenantDTO.setTenantCode("test");
            sysTenantDTO.setTenantName("123");

            return sysTenantService.addSysTenant(sysTenantDTO);
        }catch (Exception e){
            return ResultUtil.error(e);
        }

    }
}
