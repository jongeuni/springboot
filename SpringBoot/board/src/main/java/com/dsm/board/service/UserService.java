package com.dsm.board.service;
import com.dsm.board.repository.UserRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 드라이버 load
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("JDBC 에러");
            e.printStackTrace();
        }
    }

    // 회원가입, db에 정보 넣기
    public void JoinInsert(String id, String pw, String name, int age, String introduce) {
        String url = "jdbc:mysql://localhost/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sql = "INSERT INTO user (id, pw, name, age, introduce, idpw) VALUES (?,?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, user, password);
            // db에 접속할 수 있도록 해준다. 접속에 필요한 정보인 db url, username, pw를 보내야 한다
            pstmt = conn.prepareStatement(sql);
            // Connection 객체의 pre~ 메소드의 매개변수로 sql 문을 보내준다
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setInt(4, age);
            pstmt.setString(5, introduce);
            pstmt.setString(6, id + pw);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //로그인, 시큐리티 사용 ////////
    /*
    public void configure(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeRequests();
    }*/

    // 로그인, db에 정보 조회. id와 pw둘 다 맞으면 true 아니면 flase return
    public boolean loginSelect(String id, String pw) {
        String url = "jdbc:mysql://localhost/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sql = "SELECT CONCAT(id,pw) AS idpw FROM user";

        try {
            // 드라이버 호출, MySQL 서버 연결
            conn = DriverManager.getConnection(url, user, password);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("idpw").equals(id + pw)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("로그인 시 DB 연결 오류");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // 회원탈퇴
}
