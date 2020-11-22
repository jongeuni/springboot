package com.dsm.board.repository;

public class UserLoginRepository {
    private String id;
    private String pw;

    public UserLoginRepository() {}
    public UserLoginRepository(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }
}
