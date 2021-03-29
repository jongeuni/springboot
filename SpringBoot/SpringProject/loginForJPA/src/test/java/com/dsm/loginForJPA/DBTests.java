package com.dsm.loginForJPA;

import com.dsm.loginForJPA.domain.entity.UserEntity;
import com.dsm.loginForJPA.domain.repository.MemberRepository;
import com.dsm.loginForJPA.domain.repository.UserRepository;
import com.dsm.loginForJPA.service.HasingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBTests {
    @Autowired
    UserRepository userRepo;
    @Autowired
    MemberRepository memberRepository;

    //@Test
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

    @Test
    public void changPw(){
        HasingService hs = new HasingService();

        String willPw = "1111";
        // String willPwEncrypt = hs.pwEncrypt(willPw); 비밀번호 암호화
        UserEntity ue = memberRepository.findByNumber(1);
        // 전체에서 pw 검색해야 함.
        if(memberRepository.findByPw(willPw)!=null){
            System.out.println("이미 있는 pw");
            return;
        }

        ue.setPw(willPw);
        //ue.setPw(hs.pwEncrypt(ue.getPw())); // 암호화해서 다시 저장
        try {
            memberRepository.changePw(willPw, ue.getNumber()); // 에러
            System.out.println("비밀번호 변경 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("비밀번호 변경 실패");
        }


    }

}
