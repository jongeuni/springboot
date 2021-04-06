package com.dsm.image00.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="pr_image_upload")
public class FilesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fno;

    String filename;
    String fileOriName;
    String fileurl;
//    @Override
//    public String toString(){
//        return "UploadFile [id=" +  + ", fileName=" + fileName + ", size=" + size + ", mimeType=" + mimeType
//                + ", insertDate=" + insertDate + "]";
//    }
}
