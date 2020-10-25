package com.dsm.board.dao;
import com.dsm.board.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    // forname은 문자열로부터 class 객체를 얻는다.

    public List<UserDto> loginSelect(){
        List<UserDto> userList = new ArrayList<>();
        return userList;
    }
}
