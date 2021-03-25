package com.dsm.loginForJPA.service;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String join(UserEntity userEntity){
        HasingService hs = new HasingService();
        String pw = hs.pwEncrypt(userEntity.getPw()); // 비밀번호 암호화
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

        return "회원가입 완료 " + userEntity.getId();

    }

}
