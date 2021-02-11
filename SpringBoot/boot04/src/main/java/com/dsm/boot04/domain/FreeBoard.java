package com.dsm.boot04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_freeboards")
@EqualsAndHashCode(of="bno")
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키생성을 db에 위임
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp //insert시 시간 자동 저장

    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @OneToMany // 일대다관계
    private List<FreeBoardReply> replies;
}
