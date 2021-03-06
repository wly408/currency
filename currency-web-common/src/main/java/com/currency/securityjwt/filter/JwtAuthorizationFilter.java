package com.currency.securityjwt.filter;

import com.currency.bean.LoginContext;
import com.currency.dto.sys.SysUserDTO;
import com.currency.exception.IllegalRequestException;
import com.currency.securityjwt.bean.JwtUser;
import com.currency.securityjwt.config.SecurityJwtConfig;
import com.currency.securityjwt.common.utils.JwtTokenUtils;
import com.currency.sys.service.ISysUserService;
import com.currency.utils.LoginContextUtil;
import com.currency.utils.SpringUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author shuang.kou
 * @description 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final StringRedisTemplate stringRedisTemplate;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String url = request.getRequestURI();
        String[] whiteList = SecurityJwtConfig.getInstance().getWhiteList();
        String[] blackList = SecurityJwtConfig.getInstance().getBlackList();
        if(blackList!=null&&blackList.length>0){
            for (String blackUrl : blackList) {
                if(url.indexOf(blackUrl)>-1){
                    throw new IllegalRequestException("非法请求的地址");
                }
            }
        }

        LoginContextUtil.clearLoginContext();
        //1.从头部获取token
        String token = request.getHeader(SecurityJwtConfig.getInstance().getTokenHeader());
        //2.token为空或者不符合规则直接放行？
        if (token == null || !token.startsWith(SecurityJwtConfig.getInstance().getTokenPrefix())) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        String tokenValue = token.replace(SecurityJwtConfig.getInstance().getTokenPrefix(), "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            String claimsId = JwtTokenUtils.getId(tokenValue);
            String previousToken = stringRedisTemplate.opsForValue().get(claimsId);

            if (!token.equals(previousToken)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            authentication = JwtTokenUtils.getAuthentication(tokenValue);
            //获取当前登录用户
            ISysUserService sysUserService = SpringUtil.getBean(ISysUserService.class);

            SysUserDTO sysUserDTO = sysUserService.getSysUserByUserId(claimsId);


            LoginContext loginContext = new LoginContext();
            loginContext.setUserId(sysUserDTO.getUserId());
            loginContext.setUserCode(sysUserDTO.getUserCode());
            loginContext.setUserName(sysUserDTO.getUserName());
            loginContext.setUserType(sysUserDTO.getUserType());
            loginContext.setTenantId(sysUserDTO.getTenantId());
            //获取用户角色权限
            loginContext.setRoleList(JwtUser.getUserRole(claimsId));

            LoginContextUtil.setLoginContext(loginContext);

            //刷新token
            stringRedisTemplate.opsForValue().set(claimsId, token, SecurityJwtConfig.getInstance().getExpiration(), TimeUnit.SECONDS);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}


