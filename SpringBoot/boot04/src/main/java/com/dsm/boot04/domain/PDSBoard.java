package com.dsm.boot04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_pds")
@EqualsAndHashCode(of = "pid")
public class PDSBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pname;
    private String pwriter;

    @OneToMany(cascade = CascadeType.ALL) // 영속성 전이 설정
    @JoinColumn(name = "pdsno") // 존재하는 테이블에 컬럼 추가
    private List<PDSFile> files;

}
