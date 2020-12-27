package com.dsm.boot02.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class Board {
    private Long bno;
    private String title;
    private String writer;
    private String content;

    private Timestamp regdata;
    private Timestamp updatedate;
}
