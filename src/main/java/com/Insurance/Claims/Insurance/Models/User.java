package com.Insurance.Claims.Insurance.Models;

import java.sql.Date;

public class User {
    private int userId;
    private String userName;
    private Date userCDate;
    private String userPwd;
    private String userType;
    private String userStatus;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int userId, String userName, Date userCDate, String userPwd, String userType, String userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userCDate = userCDate;
        this.userPwd = userPwd;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    // Getter for userId
    public int getUserId() {
        return userId;
    }

    // Setter for userId
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter for userCDate
    public Date getUserCDate() {
        return userCDate;
    }

    // Setter for userCDate
    public void setUserCDate(Date userCDate) {
        this.userCDate = userCDate;
    }

    // Getter for userPwd
    public String getUserPwd() {
        return userPwd;
    }

    // Setter for userPwd
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    // Getter for userType
    public String getUserType() {
        return userType;
    }

    // Setter for userType
    public void setUserType(String userType) {
        this.userType = userType;
    }

    // Getter for userStatus
    public String getUserStatus() {
        return userStatus;
    }

    // Setter for userStatus
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
               "userId=" + userId +
               ", userName='" + userName + '\'' +
               ", userCDate=" + userCDate +
               ", userPwd='" + userPwd + '\'' +
               ", userType='" + userType + '\'' +
               ", userStatus='" + userStatus + '\'' +
               '}';
    }
}
