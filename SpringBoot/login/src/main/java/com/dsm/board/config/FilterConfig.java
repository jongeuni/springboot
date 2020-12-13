package com.dsm.board.config;

import com.dsm.board.filter.FilterOne;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

// FilterRegistrationBean을 사용해 필터 생성
@Configuration
public class FilterConfig /*implements WebMvcConfigurer*/ {
   /* @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new FilterOne());
        registrationBean.setUrlPatterns(Arrays.asList("/board/*"));
        return registrationBean;
    }

    */
}
