package com.example.test.model;

import java.sql.Time;

public class Login {
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private boolean adminIsLogin;
    private Time adminJam;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean isAdminIsLogin() {
        return adminIsLogin;
    }

    public void setAdminIsLogin(boolean adminIsLogin) {
        this.adminIsLogin = adminIsLogin;
    }

    public Time getAdminJam() {
        return adminJam;
    }

    public void setAdminJam(Time adminJam) {
        this.adminJam = adminJam;
    }
}