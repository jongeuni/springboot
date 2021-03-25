package com.dsm.loginForJPA.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HasingService {
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
