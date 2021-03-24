package com.dsm.loginForJPA.domain.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "user_info")
@Setter
@Getter
public class UserEntity {
    @Id
    @Column(name="u_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;


    @Column(name="u_email")
    private String email;
    @Column(name="u_id")
    private String id;
    @Column(name="u_pw")
    private String pw;
    @Column(name="u_name")
    private String name;
    @Column(name="u_nickname")
    private String nickname;
    @Column(name="u_age")
    private int age;
    @Column(name="u_introduce")
    private int introduce;

    @CreationTimestamp // insert 시 시간 자동 저장
    private Timestamp insert_time;
    @UpdateTimestamp
    private Timestamp update_time;

}
