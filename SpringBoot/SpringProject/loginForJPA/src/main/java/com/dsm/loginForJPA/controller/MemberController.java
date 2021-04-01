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
    @PatchMapping("/changPw") // 변경할 pw만 받으면 됨.나중에 수정할 것.
    public String changePw(@RequestBody UserEntity userEntity, HttpServletRequest request){
        HasingService hs = new HasingService();
        HttpSession session = request.getSession(false);
        if(session==null){
            return "로그인되지 않은 사용자";
        }
        int number = (int) session.getAttribute("user_num");
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
            // 비밀번호 변경시 세션 다시 설정
        } catch (Exception e) {
            e.printStackTrace();
            return "비밀번호 변경 실패";
        }
    }

    // 회원탈퇴
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("pwCheck") String pwCheck, HttpServletRequest request){

        HasingService hs = new HasingService();
        HttpSession session = request.getSession(false);
        if(session==null){
            return "로그인 되지 않은 사용자";
        }
        int number = (int) session.getAttribute("user_num");
        System.out.println("number      "+number); // user number


        UserEntity ue = memberRepository.findByNumber(number);
        System.out.println("로그인 전체 정보 출력 "+ue);

        System.out.println(pwCheck); // 문제!

        String pwCheckEncrypt = hs.pwEncrypt(pwCheck);
        System.out.println(pwCheckEncrypt+"      "+ue.getPw());
        if(pwCheckEncrypt.equals(ue.getPw())){
            memberRepository.deleteByNumber(number); // 컬럼 삭제
            return "회원 탈퇴 완료";
        } else{
            return "비밀번호가 일치하지 않습니다.";
        }
    }



}
