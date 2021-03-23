package com.dsm.boot9.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity // 정상적으로 bean으로 인식되도록
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    DsmUserService dsmUserService;
    //DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config........................");

        http.authorizeRequests().antMatchers("/boards/list").permitAll().antMatchers("/boards/register").hasAnyRole("BASIC", "MANAGER", "ADMIN");


        http.formLogin().loginPage("/login"); // 스프링 시큐리티에서 제공하는 기본 로그인 화면을 본다
        http.exceptionHandling().accessDeniedPage("/accessDenied");

        //세션 무효화 (로그아웃 처리)
        http.authorizeRequests().antMatchers("/logout/**").hasAnyRole("MANAGER","ADMIN"); // guest는 로그인 할 필요가없어서...
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

        http.rememberMe().key("lje").userDetailsService(dsmUserService)
        .tokenRepository(getJDBCRepository()).tokenValiditySeconds(60*60*24); // 쿠키생성
    }

    private PersistentTokenRepository getJDBCRepository(){
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder auth) throws Exception{
        log.info("build Auth globla.........");

        auth.userDetailsService(dsmUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
