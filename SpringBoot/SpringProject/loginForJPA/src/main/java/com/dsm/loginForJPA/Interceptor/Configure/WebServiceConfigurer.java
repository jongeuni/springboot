package com.dsm.loginForJPA.Interceptor.Configure;

import com.dsm.loginForJPA.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebServiceConfigurer implements WebMvcConfigurer {

    //@Autowired
    //LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                //.excludePathPatterns("")
                .addPathPatterns("/user/*"); // 여기 설정한 url ㅇ요청을 지정한 interceptor이 가로챔
    }

}
