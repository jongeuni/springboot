package com.dsm.boot9;

import com.dsm.boot9.LoginCheckInterceptor.LoginCheckInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
