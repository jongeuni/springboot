package com.dsm.loginForJPA;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBTests {
    @Autowired
    UserRepository userRepo;

    @Test
    public void insertFirst(){
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(17);
        userEntity.setEmail("lje@gmail.com");
        userEntity.setId("whddms");
        userEntity.setName("이종은");
        userEntity.setPw("whddms");
        System.out.println("저장 시도");
        userRepo.save(userEntity);
    }

}
