package com.dsm.board.controller;
import com.dsm.board.dao.UserDao;
import com.dsm.board.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//로그인과 회원가입 시
@Controller
@RequestMapping("/newJoin")
public class UserController {

    private UserDao ud;

    @Autowired
    public UserController(UserDao ud){ this.ud = ud;}



    // 회원가입
    @GetMapping(value = "info/{id}/{pw}/{name}/{age}/{introduce}")
    public String UserInsert(
            @PathVariable("id") String id,
            @PathVariable("pw") String pw,
            @PathVariable("name") String name,
            @PathVariable("age") int age, @PathVariable("introduce") String introduce){

        ud.JoinInsert(id, pw, name, age, introduce);

        return "login"; // 로그인 페이지 리턴
    }

    // 우선 디비 만들기

}
