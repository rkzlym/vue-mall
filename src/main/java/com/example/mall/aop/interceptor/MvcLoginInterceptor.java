package com.example.mall.aop.interceptor;

import com.example.mall.config.JwtProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcLoginInterceptor implements WebMvcConfigurer {

    private final JwtProperties prop;

    public MvcLoginInterceptor(JwtProperties prop) {
        this.prop = prop;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor(prop));
        registration.addPathPatterns("/**")
                .excludePathPatterns("/auth/**", "/noLogin/**");
    }
}
