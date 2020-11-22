package com.dsm.board.service;
import com.dsm.board.repository.UserLoginRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
    public boolean JoinInsert(String id, String pw, String name, int age, String introduce) {
        String url = "jdbc:mysql://localhost/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sqlCheckId = "SELECT id FROM user where (id) like (?)";
        String sql = "INSERT INTO user (id, pw, name, age, introduce) VALUES (?,?,?,?,?)";

        try {
                conn = DriverManager.getConnection(url, user, password);
                // db에 접속할 수 있도록 해준다. 접속에 필요한 정보인 db url, username, pw를 보내야 한다
                pstmt = conn.prepareStatement(sqlCheckId);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                if(rs.next()){
                    rs.close();
                    return false;
                } else{
                    pstmt = conn.prepareStatement(sql); // prepareStatement 객체 생성
                    // Connection 객체의 pre~ 메소드의 매개변수로 sql 문을 보내준다
                    pstmt.setString(1, id);
                    pstmt.setString(2, pw);
                    pstmt.setString(3, name);
                    pstmt.setInt(4, age);
                    pstmt.setString(5, introduce);
                    pstmt.executeUpdate(); // sql문 보내기
                }
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
        return true;
    }

    // 로그인, db에 정보 조회. id와 pw둘 다 맞으면 true 아니면 flase return
    public String loginSelect(UserLoginRepository userLiginInfo) {
        String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sql = "SELECT * FROM user where (id) like (?)";

        try {
            // 드라이버 호출, MySQL 서버 연결
            conn = DriverManager.getConnection(url, user, password);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userLiginInfo.getId());
            //pstmt.executeUpdate();
            rs = pstmt.executeQuery(); // 여기서 쿼리 실행
            if(rs.next()){
                if (rs.getString("pw").equals(userLiginInfo.getPw())) {
                    JwtService jwtService = new JwtService();
                    String token = jwtService.creatJwt(userLiginInfo);
                    return token;
                    //return "로그인 완료";


                } else{
                    return "pw가 틀립니다";
                }

            } else{
                return "조회된 id가 없습니다"; // 조회된 데이터 없음 == id가 틀림
            }

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
        return "false";
    }


    // 회원탈퇴
    public boolean userDelete(String MemberId, String pwCheck){
        String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sqlCheckId = "SELECT id FROM user where (id) like (?)";
        String sql = "DELETE * FROM user";




        return true;
    }
}
