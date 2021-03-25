package com.dsm.loginForJPA;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SpringBootTest
public class HasingTests {
    @Test
    public void pwEncrypt(){
        String pw = "password0000";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes());
            System.out.println("md digest"+ Arrays.toString(md.digest())); // 출력
            StringBuilder sb = new StringBuilder();
            for(byte b: md.digest()){
                sb.append(String.format("%02x",b));
            }
            System.out.println("암호화된 pw"+sb.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("비밀번호 암호화 실패");
        }
    }

}
