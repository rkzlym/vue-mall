package com.example.mall.aop.interceptor;

import com.example.mall.config.JwtProperties;
import com.example.mall.domain.constans.MdcConstants;
import com.example.mall.domain.vo.UserVo;
import com.example.mall.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtProperties prop;

    public LoginInterceptor(JwtProperties prop) {
        this.prop = prop;
    }

    /**
     * 访问需要登录的接口时执行此拦截方法
     * 解析Token，如成功将用户名存入MDC，如失败则拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies.length == 0) return false;

        for (Cookie cookie : cookies) {
            if (!StringUtils.equals(cookie.getName(), "MALL_TOKEN")) continue;
            try {
                UserVo user = JwtUtils.getInfoFromToken(cookie.getValue(), prop.getPublicKey());
                MDC.put(MdcConstants.MDC_KEY_USERNAME, user.getUsername());
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
