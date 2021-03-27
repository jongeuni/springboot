package com.dsm.loginForJPA.controller;

import com.dsm.loginForJPA.domain.dto.UserLoginDto;
import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserLoginRepository;
import com.dsm.loginForJPA.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserLoginController {
    @Autowired
    UserLoginRepository userLoginRepository;
    @GetMapping("/loginUser")
    public String loginUser(@RequestBody UserEntity user, HttpServletRequest request){

        System.out.println(user.getId());
        if (userLoginRepository.findByIdAndPw(user.getId(),user.getPw())==null){
            return "일치하는 사용자 없음";
        }
       HttpSession session = request.getSession(true); // 세션 선언
        session.setAttribute("USER", user.getId()); // 세션 값 등록
        System.out.println(session);

        // 세션 이용...

        return "로그인 성공";

    }


    public void logout(HttpSession session){
        session.invalidate(); // 세션 정보 초기화
    }
}
