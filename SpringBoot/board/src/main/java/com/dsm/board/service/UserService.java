package com.dsm.board.service;
import com.dsm.board.utils.form.UserLoginForm;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

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

        String sqlCheckId = "SELECT id FROM user where (id) like (?) OR (pw) like (?)";
        String sql = "INSERT INTO user (id, pw, name, age, introduce) VALUES (?,?,?,?,?)";

        try {
                conn = DriverManager.getConnection(url, user, password);
                // db에 접속할 수 있도록 해준다. 접속에 필요한 정보인 db url, username, pw를 보내야 한다
                pstmt = conn.prepareStatement(sqlCheckId);
                pstmt.setString(1, id);
                pstmt.setString(2,pwEncrypt(pw));
                rs = pstmt.executeQuery();
                if(rs.next()){
                    rs.close();
                    return false; // 중복된 id나 pw가 있을 때
                } else{
                    pstmt = conn.prepareStatement(sql); // prepareStatement 객체 생성
                    // Connection 객체의 pre~ 메소드의 매개변수로 sql 문을 보내준다
                    pstmt.setString(1, id);
                    pstmt.setString(2, pwEncrypt(pw)); //비밀번호 암호화  // 비밀번호 중복 처리 안했다.
                    System.out.println("암호화 전 비밀번호: "+pw);
                    System.out.println("암호화된 비밀번호: "+pwEncrypt(pw));
                    pstmt.setString(3, name);
                    pstmt.setInt(4, age);
                    pstmt.setString(5, introduce);
                    pstmt.executeUpdate(); // sql문 보내기
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    // 비밀번호 재설정 먼저 아이디랑 이름값입력받고 일치하면 비밀번호 재설정
    public String findPw(String id, String name){
        return "비밀번호 재설정 페이지";
    }
    // 비밀번호 재설정

    // 아이디 찾기 return id
    public String findId(String pw){
        System.out.println("pw : " + pw);
        String url="jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
        String user="root";
        String password = "0818";
        String sql = "SELECT id FROM user WHERE (pw) = (?)";

        try {
            conn=DriverManager.getConnection(url,user,password);
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,pwEncrypt(pw));
            rs=pstmt.executeQuery();
            System.out.println("rs is null : " + rs);
            if(rs.next()){
                return rs.getString("id");
            } else{
                System.out.println("암호화된 비밀번호: "+pwEncrypt(pw));
                return "비밀번호를 잘못 입력";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("아이디 찾기 db 연결 오류");
        } finally {
            try{
                if(conn!=null) conn.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return "아이디를 찾을 수 없습니다.";
    }

    // 비밀번호 재설정 전 일치하는 계정 확인
    public Boolean verifyPresenceAccount(String id, String name){
        String url = "jdbc:mysql://localhost/board?serverTimezone=UTC";
        String user ="root";
        String password = "0818";
        String sql = "SELECT * FROM user WHERE (id) like (?) AND (name) like (?)"; // 아이디와 이름이 일치하는 계정이있는지확인

        try{
            conn = DriverManager.getConnection(url, user, password);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2,name);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return true; // 일치하는 계정이 있는 경우
            } else{
                System.out.println("일치하는 계정 찾지 못함");
                return false; // 일치하는 계정이 없는 경우
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {

                if (conn != null) conn.close();
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    // 비밀번호 재설정
    // 비밀번홉 바꿀 계정이랑 바꿀 패스워드
    public Boolean resetPw(String id, String changePw){
        String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sqlPwredundancyCheck = "SELECT * FROM user WHERE (pw) = (?)";
        String sql = "UPDATE user SET pw = ? WHERE id = ?"; // 아이 ㅜㅜ 디 ㅜㅜ 일치하는 계정에 패스워드 변경 ㅜ
        try{
            conn=DriverManager.getConnection(url,user,password);
            pstmt=conn.prepareStatement(sqlPwredundancyCheck);
            pstmt.setString(1,pwEncrypt(changePw));
            rs=pstmt.executeQuery();
            if(rs.next()){
                System.out.println("중복되는 pw가 있음");
                return false; // 패스워드가 중복됨.
            } else{
                pstmt=conn.prepareStatement(sql);
                System.out.println(changePw+"   암호화된 PW: "+pwEncrypt(changePw));
                pstmt.setString(1,pwEncrypt(changePw));
                pstmt.setString(2,id);
                pstmt.executeUpdate();
                return true;
            }
        } catch(SQLException e ){
            e.printStackTrace();
            System.out.println("비밀번호 재설정 시 db 연결 오류");
        } finally {
            try{
                if(conn!=null) conn.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    // 로그인, db에 정보 조회. id와 pw둘 다 맞으면 true 아니면 flase return
    public String loginSelect(UserLoginForm userLiginInfo) {
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
                if (rs.getString("pw").equals(pwEncrypt(userLiginInfo.getPw()))) {
                    JwtService jwtService = new JwtService();
                    String token = jwtService.creatJwt(userLiginInfo.getId());
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

    // 비밀번호 재설정 (현재 비밀번호 받아서 일치할 시 변경)

    // 회원탈퇴
    public String userDelete(String MemberId, String pwCheck){
        String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
        String user = "root";
        String password = "0818";
        String sqlCheckId = "SELECT * FROM user WHERE (id) like (?)"; //아이디와 맞는 패스워드 찾기
        String sql = "DELETE FROM user WHERE (pw) = (?)"; //아이디 삭제
    try{
        conn=DriverManager.getConnection(url, user, password);
        pstmt = conn.prepareStatement(sqlCheckId);
        pstmt.setString(1,MemberId);
        rs = pstmt.executeQuery();

        if(rs.next()){
            //System.out.println("아이디="+rs.getString("id")+"비번="+rs.getString("pw"));
            // id 중복 가능하다는점.
            if(rs.getString("pw").equals(pwEncrypt(pwCheck))){

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,pwEncrypt(pwCheck));
                pstmt.executeUpdate();
                return "회원탈퇴 완료";
            }else{
                return "비밀번호가 틀렸습니다.";
            }
        }else{
            return "id가 틀렸는데";
        }
    } catch(SQLException e){
        System.out.println("회원탈퇴 시 DB 연결 오류");
        e.printStackTrace();
    } finally{
        try {
            if(conn!=null){
                conn.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    return "회원 탈퇴 실패";
    }

    // 비밀번호 암호화 해주는 메소드
    public String pwEncrypt(String pw)  {
        String encryptedPasswor="";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //시큐리티인데 사용가능?
            md.update(pw.getBytes());
            StringBuilder builder = new StringBuilder();
            for(byte b:md.digest()){
                builder.append(String.format("%02x",b));
            }
            encryptedPasswor = builder.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            System.out.println("비밀번호 암호화 실패");
        }
        return encryptedPasswor;

/*
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b:md.digest()){
           builder.append(String.format("%02x",b));
        }
        return builder.toString();*/




        /*
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte b:md.digest()){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();

         */


    }
}
