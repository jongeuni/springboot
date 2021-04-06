package com.dsm.image00.service;

import com.dsm.image00.domain.FilesEntity;
import com.dsm.image00.repository.FilesRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
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
    public ResponseEntity uploadFiles(MultipartFile file){
        if(file == null){
            return new ResponseEntity("파일이 비어있음.",HttpStatus.BAD_REQUEST); // 파일 비어있을 경우
        }

        File dir = new File(uploadPath);
        if(dir.exists()!=false){ // 파일 존재 여부를 알 수 있다
            dir.mkdir(); // 파일 존재 안 하면 만들기
        }

        try{

            //String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 확장자

            String saveName = getRandomString()+file.getOriginalFilename(); // 서버에 저장할 파일명, 중복되면 안 됨

            File target = new File(uploadPath, saveName); // 업로드 경로와 파일명 담긴 파일 객체 생성
            file.transferTo(target);  // upload 경로에 saveName과 동일한 이름 가진 파일 생성

            FilesEntity filesEntity = new FilesEntity();
            filesEntity.setFilename(saveName);
            filesEntity.setFileOriName(file.getOriginalFilename());
            filesEntity.setFileurl(target.toString());

            filesRepository.save(filesEntity);
        } catch(IOException e){
            return new ResponseEntity("파일이 발견되지 않음. 저장 안 됨.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("파일 업로드 완료.",HttpStatus.OK);
    }

    public ResponseEntity<Resource> downloadFile(int num, HttpServletRequest request) throws MalformedURLException {
        FilesEntity fileEntity = filesRepository.findByFno(num);

        Path filePath = Paths.get(fileEntity.getFileurl()).normalize();

            Resource resource = new UrlResource(filePath.toUri());


        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(
                    resource.getFile().getAbsolutePath()
            );
        }catch(IOException e){
            contentType = "applicatoin/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+fileEntity.getFilename()+"\""
                )
                .body(resource);

    }
}
