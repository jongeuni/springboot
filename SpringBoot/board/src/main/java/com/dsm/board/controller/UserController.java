package com.dsm.board.controller;
import com.dsm.board.repository.UserRepository;
import com.dsm.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

//로그인과 회원가입 시
@Controller
@RequestMapping("/board")
public class UserController {

    private UserService us;

    @Autowired
    public UserController(UserService us){ this.us = us;}

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
            us.JoinInsert(userinfo.getId(),userinfo.getPw(), userinfo.getName(),userinfo.getAge(), userinfo.getIntroduce());
            return "회원 가입 성공";
        }
        return "회원 가입 실패";
    }
    /*
    @PostMapping(value = "info/{id}/{pw}/{name}/{age}/{introduce}") //request body에 넣기
    public String UserInsert(
            @PathVariable("id") String id,
            @PathVariable("pw") String pw,
            @PathVariable("name") String name,
            @PathVariable("age") int age, @PathVariable("introduce") String introduce){

        us.JoinInsert(id, pw, name, age, introduce);

        return "login"; // 로그인 페이지 리턴
    }*/

    // 로그인, 조회(select, read)
    @GetMapping("/login")
    @ResponseBody
    public String UserLogin(@ModelAttribute("id") String id, @ModelAttribute("pw") String pw){
        //ud.UserSelect(id, pw);
        if(us.loginSelect(id, pw)){
            return "로그인 완료";
        } else{
            return "로그인 실패";
        }

    }
    /*
    @GetMapping(value = "loginInfo/{id}/{pw}")
    @ResponseBody
    public String UserSelect(@PathVariable("id") String id,
                             @PathVariable("pw") String pw){
        //ud.UserSelect;

        return "로그인 완료";
    }*/

    // 회원탈퇴, 삭제(delete)
}
