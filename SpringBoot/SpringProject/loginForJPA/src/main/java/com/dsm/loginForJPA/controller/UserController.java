package com.dsm.loginForJPA.controller;

import com.dsm.loginForJPA.service.UserService;
import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserRepository;
import com.dsm.loginForJPA.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService us;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/joinUser")
    public String joinUser(@RequestBody UserEntity userEntity){
        if(userEntity.getId().equals("")||userEntity.getPw().equals("")||userEntity.getName().equals("")||userEntity.getEmail().equals("")){
            return "입력해야 할 값 없음";
        }

        return us.join(userEntity);
    }





}




