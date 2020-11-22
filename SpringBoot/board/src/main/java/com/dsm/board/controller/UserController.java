package com.dsm.board.controller;
import com.dsm.board.repository.UserLoginRepository;
import com.dsm.board.repository.UserRepository;
import com.dsm.board.service.JwtService;
import com.dsm.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

//로그인과 회원가입 시
@Controller
@RequestMapping("/board")
public class UserController {

    private UserService us;
    //JwtService jwtService;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    // 첫 페이지
    @GetMapping("/main")
    public String returnMain(){
        return "main";
    }

    // 회원가입, 생성(insert, create)
    @PostMapping("/join")
    @ResponseBody
    public String UserInsert(@RequestBody UserRepository userinfo){
        if(userinfo.pwcheack.equals(userinfo.getPw())){
            if(us.JoinInsert(userinfo.getId(),userinfo.getPw(), userinfo.getName(),userinfo.getAge(), userinfo.getIntroduce())){ //만약 return 값이 flase면
                return "회원 가입 성공";
            } else{
                return "아이디가 중복됩니다";
            }
        }
        return "회원 가입 실패 - 패스워드 확인을 다시 해주세요";
    }

    // 로그인, 세션 사용

    // 로그인, 조회(select, read)
    @PostMapping("/login")
    @ResponseBody
    public String UserLogin(@RequestBody UserLoginRepository userLoginInfo){

        //String check = us.loginSelect(userLoginInfo.getId(), userLoginInfo.getPw());
        String check = us.loginSelect(userLoginInfo);

        return check;
        /*
        ud.UserSelect(id, pw);
        if(us.loginSelect(id, pw)){
            return "로그인 완료";
        } else{
            return "로그인 실패";
        }*/
    }

    //회원탈퇴
    @DeleteMapping("/deleteMember")
    @ResponseBody
    public String userDelete(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String pwCheck = request.getHeader("PwCheck"); // 회원탈퇴시 받는 비밀번호
        JwtService js = new JwtService();
        String MemberId = js.getIdFromToken(token);
        us.userDelete(MemberId, pwCheck);
        //jwtService.getIdFromToken(token);
        //jwtService.getIdFromToken(token); // 토큰에서 아이디 빼오기
        return "회원탈퇴 완료, 아직 db는 안했지만";
    }


    // 회원탈퇴, 삭제(delete)
    /*@PostMapping("/deleteMember")
    public String UserDelete(String ChcekPw, @RequestHeader User) // 토큰과 탈퇴시 입력한 비밀번호를 받음.
*/
}

