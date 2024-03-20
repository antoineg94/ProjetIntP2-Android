package com.example.projetintp2_android;

public class LoginResponse {
    private String token ;
     Users user;


    private String message;

    public LoginResponse(String token,Users users) {
        this.token = token;
        this.user=users;

    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }



}
