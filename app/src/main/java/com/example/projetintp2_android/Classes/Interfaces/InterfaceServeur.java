package com.example.projetintp2_android.Classes.Interfaces;

import com.example.projetintp2_android.Classes.Objects.Prescriptions;
import com.example.projetintp2_android.Classes.APIResponses.LoginResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceServeur {
    @POST("/projet/laravel/public/api/register")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("name") String nom,
                                @Field("email") String email,
                                @Field("password") String psw );


    @POST("/projet/laravel/public/api/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String nom,
                              @Field("password") String psw);
    @POST("/projet/laravel/public/api/logout")
    Call<LoginResponse> logout();

    @POST("/projet/laravel/public/api/update")
    @FormUrlEncoded
    Call<ResponseBody> update(@Field("name") String nom,
                              @Field("email") String email,
                              @Field("password") String psw );
    @POST("/projet/laravel/public/api/updateName")
    @FormUrlEncoded
    Call<ResponseBody> updateName(@Field("name") String nom);


    @POST("/projet/laravel/public/api/updateEmail")
    @FormUrlEncoded
    Call<ResponseBody> updateEmail(@Field("email") String email);

    @POST("/projet/laravel/public/api/updatePassword")
    @FormUrlEncoded
    Call<ResponseBody> updatePassword(@Field("passord") String password);

 //   @GET("alluser")
  //  Call<ResponseBody> getUser();


    @GET("fr/prescriptions")
    Call<List<Prescriptions>> getMedicaments();


}
