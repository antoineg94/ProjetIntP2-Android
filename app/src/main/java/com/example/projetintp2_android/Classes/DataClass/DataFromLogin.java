package com.example.projetintp2_android.Classes.DataClass;

import com.example.projetintp2_android.Classes.Objects.UserV2;

public class DataFromLogin extends  Data{
    UserV2 user;
    String token;

    public DataFromLogin(UserV2 user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserV2 getUser() {
        return user;
    }

    public void setUser(UserV2 user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DataFromLogin{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public Data getData() {
        return this;
    }


}
