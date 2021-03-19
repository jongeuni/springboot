package com.dsm.boot8.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log
@EnableWebSecurity // 정상적으로 bean으로 인식되도록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config........................");

        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        http.formLogin().loginPage("/login"); // 스프링 시큐리티에서 제공하는 기본 로그인 화면을 본다
        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder auth) throws Exception{
        log.info("build Auth global.........");

        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("1111")
                .roles("MANAGER");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }
}
