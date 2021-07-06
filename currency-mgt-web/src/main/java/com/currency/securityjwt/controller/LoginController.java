package com.currency.securityjwt.controller;

import com.currency.securityjwt.bean.LoginRequest;
import com.currency.securityjwt.common.constants.SecurityConstants;
import com.currency.securityjwt.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuliangyong
 * @description 认证授权
 **/
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "认证")
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.createToken(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, token);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public ResponseEntity<Void> logout() {
        authService.removeToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
