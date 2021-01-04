package com.dsm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
//@Table
@Table(name="tbl_boards") //주석의 확인되지 않은 데이터베이스 참조로 빨간줄이 그어지지만 잘 돌아간다.
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regdata;
    @UpdateTimestamp
    private Timestamp updatedate;
}
