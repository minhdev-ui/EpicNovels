package com.example.myapplication.models;

import java.time.LocalDate;

public class User {
    private String name;
    private String username;
    private String password;
    private Integer gender;
    private LocalDate dob;

    public User(String name, String username, String password, Integer gender, LocalDate dob) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
