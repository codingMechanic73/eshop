package com.upgrad.eshop.dtos;

public class LoginResponse {
    private String userName;
    private String message;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String userName, String message, String token) {
        this.userName = userName;
        this.message = message;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
