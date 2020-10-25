package com.dsm.board.controller;
import com.dsm.board.dao.UserDao;
import com.dsm.board.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//로그인과 회원가입 시
@Controller
@RequestMapping("/newJoin")
public class UserController {

    private UserDao ud;

    public UserController(){
        List<UserDto> userLiset = ud.loginSelect();
    }

    // 우선 디비 만들기

}
