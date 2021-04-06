package com.dsm.image00.controller;


import com.dsm.image00.domain.FilesEntity;
import com.dsm.image00.service.TestBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;

@RestController
public class TestBController {
    @Autowired
    TestBService testBService;
    @PostMapping("/testImageUpload")
    public ResponseEntity test(MultipartFile file){

        return testBService.uploadFiles(file);
    }
    @GetMapping("/testImageDownload/{num}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer num, HttpServletRequest request) throws MalformedURLException {

        return testBService.downloadFile(num, request);
        //FilesEntity filesEntity -

    }
}
