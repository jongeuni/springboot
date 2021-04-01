package com.dsm.loginForJPA.controller;

import com.dsm.loginForJPA.domain.dto.UserLoginDto;
import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserLoginRepository;
import com.dsm.loginForJPA.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginRepository userLoginRepository;
    @Autowired
    LoginService ls;
    @PostMapping("/loginUser")
    public String loginUser(@RequestBody UserLoginDto userLogin, HttpServletRequest request){

        return ls.loginUser(userLogin, request);

    }


    public void logout(HttpSession session){
        session.invalidate(); // 세션 정보 초기화
    }
}
