package com.example.legalplatform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 注册拦截器
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册权限拦截器，拦截所有接口
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                // 放行登录接口（无需权限）
                .excludePathPatterns("/user/login");
    }
}