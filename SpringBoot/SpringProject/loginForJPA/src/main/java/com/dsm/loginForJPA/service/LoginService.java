package com.dsm.loginForJPA.service;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginService {
    @Autowired
    UserLoginRepository userLoginRepository;

    public String loginUser(UserEntity user, HttpServletRequest request){

        System.out.println(user.getId());

        if (userLoginRepository.findByIdAndPw(user.getId(),user.getPw())==null){
            return "일치하는 사용자 없음";
        }

        HttpSession session = request.getSession(true); // 세션 선언
        session.setAttribute("USER", user.getId()); // 세션 값 등록
        System.out.println(session);

        return "로그인 성공";
    }
}
