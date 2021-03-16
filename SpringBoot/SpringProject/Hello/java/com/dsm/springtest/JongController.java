package com.dsm.springtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class JongController {
    @RequestMapping("/jong")
    //@ResponseBody               // 값을 보냄
    public String jong(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        return "id : " + id + " pw : " + pw;
    }
}

// id : aaa pw : bbb.html

/*

DispacherServlet
디스패처 서블릿

데이터베이스 Database DB

웹 서버 - WAS (와스)

Web Server
WAS - Web Application Server


 */