package com.dsm.board.utils.form;

public class PasswordRequestForm {
    private String password;

    public PasswordRequestForm() {}
    public PasswordRequestForm(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
