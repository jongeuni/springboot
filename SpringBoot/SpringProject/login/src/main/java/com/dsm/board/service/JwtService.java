package com.dsm.board.service;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtService {

    // 시큐리티키 생성
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //키 생성시 사용할 알고리즘정하기

    private final String secretKey = "jmlim12345bbbbbaaaaa123456789066";
    private final  byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey); //바이트단위로나눔

    private final Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    // 토큰 생성
    public String creatJwt(final String id){
        String token = Jwts.builder()
                .setHeaderParam("typ","JWT") // alg를 key에서 설정했을 경우 따로 넣어주지 않아도 된다
                .claim("data", "로그인 검증 토큰") // 토큰에 대한 설명
                .claim("id",id) // id
                .setExpiration(new Date(System.currentTimeMillis() + (10000 * 60 * 60)))
                .signWith(signatureAlgorithm, key) // 지정된 알고리즘, 지정된 키를 사용하여 서명하고 JWS 생성
                .compact(); // 실제 jwt 제작

        return token;
    }

    //토큰에서 id 추출
    public String getIdFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        String id = claims.get("id", String.class);

        System.out.println("아이디:"+id);
        return id;
    }

}