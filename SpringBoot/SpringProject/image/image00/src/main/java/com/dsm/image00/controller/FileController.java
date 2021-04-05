package com.dsm.image00.controller;

import com.dsm.image00.domain.FilesEntity;
import com.dsm.image00.repository.FilesRepository;
import com.dsm.image00.service.FilesService;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class FileController {
    @Autowired
    FilesService filesService;
    @Autowired
    FilesRepository filesRepository;

    @RequestMapping("file/insert")
    public String Insert(){

        return "file/index.jsp";
    }

    @PostMapping("file/fileinsert")
    public String fileinsert(HttpServletRequest request, @RequestParam MultipartFile files) throws Exception{
        FilesEntity file = new FilesEntity();

        String sourceFileName = files.getOriginalFilename();

        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        File destinationFile;
        String destinationFileName;
        String fileUrl = "";

        do {
            destinationFileName = RandomString.make(33)+"."+sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);

        file.setFilename(destinationFileName);
        file.setFileOriName(sourceFileName);
        file.setFileurl(fileUrl);
        filesService.save(file);
        return "저장 완료";
    }

    @RequestMapping("file/index5")
    public Model index5(Model model){
        model.addAttribute("file",filesRepository.findByFno(8));
        if(model==null){

        }
        return model;
    }

    //
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file){
        if(file.isEmpty()){
            // 파일 업로드하지 않았을 경우 처리
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(String fileNmae){
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
