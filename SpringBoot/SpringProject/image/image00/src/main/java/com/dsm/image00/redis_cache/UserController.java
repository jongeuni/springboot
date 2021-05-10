package com.dsm.image00.redis_cache;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final RedisUtil redisUtil;


    @GetMapping("/signup")
    public void signup(@RequestParam String email){
        redisUtil.set(email, "11111", 1);

        run(email);
        //System.out.println(redisUtil.get(email));
    }
    public void run(String email){

        System.out.println(redisUtil.get(email));
    }
}
