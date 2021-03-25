package com.dsm.loginForJPA.controller;

import com.dsm.loginForJPA.Userservice.HasingService;
import com.dsm.loginForJPA.Userservice.UserService;
import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/joinUser")
    public String joinUser(@RequestBody UserEntity userEntity){
        if(userEntity.getId().equals("")||userEntity.getPw().equals("")||userEntity.getName().equals("")||userEntity.getEmail().equals("")){
            return "입력해야 할 값 없음";
        }

        String pw = pwEncrypt(userEntity.getPw()); // 비밀번호 암호화
        System.out.println("암호화된 비밀번호"+pw);
        if(userRepository.findByEmail(userEntity.getEmail())!=null){ // 중복 email
            return "중복된 email";
        }
        if(userRepository.findByPw(pw)!=null){ // 중복 pw
            return "중복된 pw";
        } else if (userEntity.getNickname()!=null){ // 닉네임이 잇을 때
            if(userRepository.findByNickname(userEntity.getNickname())!=null){
                return "중복된 nickname";
            }
        }

        userEntity.setPw(pw);
        userRepository.save(userEntity);

        return "회원가입 성공 "+userEntity.getId();
    }

    // 비밀번호 암호화
    public String pwEncrypt(String pw){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes());
            StringBuilder bilder = new StringBuilder();
            for(byte b:md.digest()){
                bilder.append(String.format("%02x",b));
            }
            return bilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("비밀번호 암호화 실패");
        }
        return "";
    }

}




