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

        HasingService hs = new HasingService();

        String hpw = hs.pwEncrypt(user.getPw()); // 암호화
        UserEntity ue = userLoginRepository.findByIdAndPw(user.getId(),hpw);
        if (ue==null){
            return "일치하는 사용자 없음";
        }

        HttpSession session = request.getSession(true); // 세션 선언
        System.out.println("number  "+ ue.getNumber());
        session.setAttribute("user_num", ue.getNumber()); // 세션 값 등록
        session.setMaxInactiveInterval(60*60); // 60분동안 세션을 유지하고 싶다면, 60 * 60으로 설정
        System.out.println(session);

        return "로그인 성공";
    }
}
