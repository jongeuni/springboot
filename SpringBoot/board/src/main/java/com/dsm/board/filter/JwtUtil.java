package com.dsm.board.filter;

import io.jsonwebtoken.Jwt;

public class JwtUtil {
    public static boolean verifyToken(String token, String secret){
        /*if(token==null){
            return false;
        }
        try{
            String result = Jwt.require(Algorithm.HMAC512(secret.getBytes()))
                    .build()
                    .verify(token.replace())
                    .getSubject();
            return true;
        } catch(Exception e){
            return false;
        }*/
        return false;
    }
}