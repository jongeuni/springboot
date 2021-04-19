package com.dsm.image00.controller;

import com.dsm.image00.domain.FilesEntity;
import com.dsm.image00.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 다중파일업로드
@RestController
@RequiredArgsConstructor // di
public class TestCController {

    private final FilesRepository filesRepository;
    // 업로드 경로
    private final String uploadPath = Paths.get("D:", "imagePr").toString();
    // 랜덤 문자열
    private final String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }


    @RequestMapping("/upload/image")
    public void requestupload2(MultipartHttpServletRequest request) {
        List<MultipartFile> fileList = request.getFiles("file");
        String src = request.getParameter("src");
        System.out.println("src value: " + src);

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본파일명
            System.out.println(mf.getOriginalFilename() + "  originFileName");

            String d;
        }

    }
    // 우선 저장 해보고



    @PostMapping("/test")
    public List<String> upload(@RequestPart List<MultipartFile> files) throws IOException {
        List<String> list = new ArrayList<>();
        for(MultipartFile file : files){
            String saveName = getRandomString()+file.getOriginalFilename();
            File dest = new File(uploadPath, saveName);
            file.transferTo(dest); // upload 경로에 saveName과 동일한 이름 가진 파일 생성

            FilesEntity filesEntity = new FilesEntity();
            filesEntity.setFilename(saveName);
            filesEntity.setFileOriName(file.getOriginalFilename());
            filesEntity.setFileurl(dest.toString());


            filesRepository.save(filesEntity);
        }
        return list;
    }
}