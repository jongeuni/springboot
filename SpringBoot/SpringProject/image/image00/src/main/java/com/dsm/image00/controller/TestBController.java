package com.dsm.image00.controller;


import com.dsm.image00.service.TestBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestBController {
    @Autowired
    TestBService testBService;
    @PostMapping("/testImageUpload")
    public void test(MultipartFile file){
        System.out.println(file+"  파이일");
        testBService.uploadFiles(file);
    }
}
