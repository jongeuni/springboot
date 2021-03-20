package com.dsm.boot8.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity // 정상적으로 bean으로 인식되도록
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    DsmUserService dsmUserService;
    //DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security config........................");

        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

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
/*
    @Autowired
    public void configuredGlobal(AuthenticationManagerBuilder auth) throws Exception{
        log.info("build Auth global.........");

        String query1="SELECT uid username, CONCAT('{noop}',upw) password, true enabled FROM tbl_members WHERE uid=?";

        String query2 = "SELECT member uid, role_name role FROM tbl_member_roles WHERE member =?";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(query1)
                .rolePrefix("ROLE_")
                .authoritiesByUsernameQuery(query2);
    }*/

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
