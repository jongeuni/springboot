package com.dsm.image00.service;

import com.dsm.image00.domain.FilesEntity;
import com.dsm.image00.repository.FilesRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class TestBService {
    @Autowired
    FilesRepository filesRepository;

    // 업로드 경로
    private final String uploadPath = Paths.get("D:", "imagePr").toString();


    // 랜덤 문자열

    private final String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    // 업로드
    public String uploadFiles(MultipartFile file){
        if(file == null){
            return "파일 비어있음";
            //FilesEntity filesEntity = new FilesEntity();
            //return filesEntity; // 파일 비어있을 경우
        }
        File dir = new File(uploadPath+"\\"+file.getOriginalFilename());
        System.out.println(dir+"  dir");
        if(dir.exists()){ // 파일 존재 여부를 알 수 있다
            System.out.println("이미 존재하는 파일");

        } else{
            dir.mkdir(); // 파일 존재 안 하면 만들기
        }

        try{

            String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 확장자


            String saveName = getRandomString()+file.getOriginalFilename();
            System.out.println(saveName);
            // 서버에 저장할 파일명, 중복되면 안됨.

            File target = new File(uploadPath, saveName);
            file.transferTo(target);
            // 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성
            System.out.println(target+"     target");

            FilesEntity filesEntity = new FilesEntity();
            filesEntity.setFilename(saveName); // 서버에 저장할 이름
            filesEntity.setFileOriName(file.getOriginalFilename());
            filesEntity.setFileurl(target.toString());

            System.out.println(filesEntity+"  파일엔티티");
            System.out.println(saveName+"   세이브네임");
            filesRepository.save(filesEntity);

        } catch(IOException e){
            System.out.println("["+file.getOriginalFilename()+"] failed to ave file...");


        } /*catch(Exception e){
            System.out.println("["+file.getOriginalFilename()+"] failed to save file...");


        }*/

        return "완료?";
    }
}
