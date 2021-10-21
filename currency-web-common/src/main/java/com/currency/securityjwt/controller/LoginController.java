package com.currency.securityjwt.controller;

import com.currency.securityjwt.bean.LoginRequest;
import com.currency.securityjwt.bean.LoginResponse;
import com.currency.securityjwt.common.utils.JwtTokenUtils;
import com.currency.securityjwt.config.SecurityJwtConfig;
import com.currency.securityjwt.service.AuthService;
import com.currency.sys.service.ISysMenuService;
import com.currency.utils.CaptchaUtil;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.ResultHandler;
import com.currency.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wuliangyong
 * @description 认证授权
 **/
@RestController
@RequestMapping("/auth")
@Api(tags = "认证")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultHandler<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try{
            String token = authService.createToken(loginRequest);
            LoginResponse response = new LoginResponse();
            response.setToken(token);
            String tokenValue = token.replace(SecurityJwtConfig.getInstance().getTokenPrefix(), "");

            response.setMenuList(sysMenuService.getUserSysMenuByUserId(JwtTokenUtils.getId(tokenValue)));
            return ResultHandler.suc(response);
        }catch (Exception e){
            return ResultHandler.error(e);
        }

    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public ResultHandler<Void> logout() {
        authService.removeToken();
        return ResultHandler.suc();
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @PostMapping("/createCode")
    @ApiOperation("获取验证码")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = UUIDUtils.getUUID();

        //通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        //必须先与服务器确认返回的响应是否被更改，然后才能使用该响应来满足后续对同一个网址的请求
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        CaptchaUtil util = CaptchaUtil.Instance();
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        stringRedisTemplate.opsForValue().set(uid, code, SecurityJwtConfig.getInstance().getVerifyCodeExpiration(), TimeUnit.SECONDS);
        response.setHeader(SecurityJwtConfig.getInstance().getVerifyCodeUid(), uid);
        request.getSession().setAttribute("code", code);
        // 输出到web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }
}
