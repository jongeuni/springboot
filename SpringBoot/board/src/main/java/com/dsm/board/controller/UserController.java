package com.dsm.board.controller;
import com.dsm.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//로그인과 회원가입 시
@Controller
@RequestMapping("/board")
public class UserController {

    private UserService ud;

    @Autowired
    public UserController(UserService ud){ this.ud = ud;}

    // 첫 페이지
    @GetMapping("/main")
    public String returnMain(){
        return "main";
    }

    // 회원가입, 생성(insert, create)
    @PostMapping(value = "info/{id}/{pw}/{name}/{age}/{introduce}") //request body에 넣기
    public String UserInsert(
            @PathVariable("id") String id,
            @PathVariable("pw") String pw,
            @PathVariable("name") String name,
            @PathVariable("age") int age, @PathVariable("introduce") String introduce){

        ud.JoinInsert(id, pw, name, age, introduce);

        return "login"; // 로그인 페이지 리턴
    }

    // 로그인, 조회(select, read)
    @GetMapping(value = "loginInfo/{id}/{pw}")
    @ResponseBody
    public String UserSelect(@PathVariable("id") String id,
                             @PathVariable("pw") String pw){
        //ud.UserSelect;

        return "로그인 완료";
    }

    // 회원탈퇴, 삭제(delete)
}
