package com.example.home;

public class Users {
    private int user_ID;
    private String name;
    private String password;
    private String type;

    public Users(){
        user_ID = 0;
        name = "";
        password = "";
    }

    public Users(int user_ID, String name, String password, String type) {
        this.user_ID = user_ID;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
