package com.dsm.image00.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class FileTestController {
    @PostMapping(value = {"/upload"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadTest01(@RequestParam("file") MultipartFile file){
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        try {
            byte[] data = file.getBytes();
            System.out.println(Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("파일 내용 비어있음");
        }

        return "success";

    }

    @PostMapping("/upload02")
    public String upload02(HttpServletRequest request){
        // 그냥 MultipartFile로 받아도 된다.
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("file");

        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        try {
            byte[] data = file.getBytes();
            System.out.println(Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("내용 빔");
        }
        return "success - upload02";

    }
}
