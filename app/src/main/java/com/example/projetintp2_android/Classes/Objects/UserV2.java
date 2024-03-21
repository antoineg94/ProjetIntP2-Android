package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_User")
public class UserV2 {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("token")
    String token;
    @SerializedName("password")
    String password;

    @SerializedName("email_verified_at")
    String email_verified_at;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public UserV2(int id, String name, String email, String token, String password, String email_verified_at, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.password = password;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
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
        return token;
    }

    public void setApi_token(String api_token) {
        this.token = api_token;
    }

}

