package com.dsm.image00.domain.Object;

import org.springframework.web.multipart.MultipartFile;

public class TestFile {
    MultipartFile file;
    String name;

    public MultipartFile getFile(){
        return file;
    }

    public void setFile(MultipartFile file){
        this.file = file;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
}
