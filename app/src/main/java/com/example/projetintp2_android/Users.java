package com.example.projetintp2_android;

public class Users {




    int id;
    String name;
    String email;

    String  api_token;

    String password;




    public Users( String name, String email, String password) {
     //   this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
       // this.api_token = api_token;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }


}
