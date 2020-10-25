package com.dsm.board.dao;
import com.dsm.board.dto.UserDto;

import java.sql.*;
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
    // 드라이버 load

    public List<UserDto> JoinInsert(String id, String pw, String name, int age, String introduce){
        List<UserDto> userList = new ArrayList<>();
        String url = "jdbc:mysql://localhost/board?serverTimezone=UTC";
        String user = "root";
        String password="0818";
        String sql = "INSERT INTO user (id, pw, name, age, introduce) VALUES (?,?,?,?,?)";

        try{
            conn = DriverManager.getConnection(url, user, password);
            // db에 접속할 수 있도록 해준다. 접속에 필요한 정보인 db url, username, pw를 보내야 한다
            pstmt = conn.prepareStatement(sql);
            // Connection 객체의 pre~ 메소드의 매개변수로 sql 문을 보내준다
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setInt(4,age);
            pstmt.setString(5, introduce);
            pstmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally{
            try{
                if(conn!=null){
                    conn.close();
                }
                if(pstmt!=null){
                    pstmt.close();
                }
                if(rs!=null){
                    rs.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return userList;
    }
}
