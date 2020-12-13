package com.dsm.bank.bank.repository;

public class User {
    private int money;
    private String name;
    private int number;

    /*User(int money, String name, int number){
        this.money=money;
        this.name=name;
        this.number=number;
    }*/

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}