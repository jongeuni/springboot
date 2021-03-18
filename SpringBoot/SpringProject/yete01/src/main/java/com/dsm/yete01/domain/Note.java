package com.dsm.yete01.domain;

import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="tbl_notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;


    private String title;
    private String content;

    private int category; // 1은 영화/책 2는 대화 3은 아이디어 4는 기타

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    public int getNumber() {
        return number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    public int getCategory(){
        return category;
    }

    public void setContent(String content){
        this.content=content;

    }
    public String getContent(){
        return content;
    }

}
