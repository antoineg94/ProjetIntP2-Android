package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_User")
public class Users {


    @PrimaryKey(autoGenerate = true )
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;


    @SerializedName("api_token")

    String  api_token;
    @SerializedName("password")
    String password;





    public Users( int id,String name, String email,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;

         this.api_token = "";
    }


  /*  public Users( String name, String email, String password) {
        //this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
       // this.api_token = api_token;
    }

   */
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
