package com.dsm.loginForJPA.controller;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.MemberRepository;
import com.dsm.loginForJPA.service.HasingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 회원 탈퇴, 비밀번호 변경, 로그아웃
@RestController
@RequestMapping("/user")
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    // 비밀번호 변경
    @GetMapping("/changPw") // 변경할 pw만 받으면 됨.나중에 수정할 것.
    public String changePw(@RequestBody UserEntity userEntity, HttpServletRequest request){
        HasingService hs = new HasingService();
        HttpSession session = request.getSession(false);
        if(session==null){
            return "로그인되지 않은 사용자";
        }
        int number = (int) session.getAttribute("user_num"); // 세션에서 값 가져오는 거 오류 0가져와짐
        System.out.println("number" + number);
        UserEntity ue = memberRepository.findByNumber(number); // 전체정보

        String willPw = hs.pwEncrypt(userEntity.getPw()); // 암호화
        if(memberRepository.findByPw(willPw)!=null){
            System.out.println("이미 있는 pw");
            return "이미 있는 pw";
        }

        try {
            memberRepository.changePw(willPw, ue.getNumber());
            return "비밀번호 변경 성공";
        } catch (Exception e) {
            e.printStackTrace();
            return "비밀번호 변경 실패";
        }
    }
}
