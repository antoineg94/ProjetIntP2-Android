package com.example.projetintp2_android.Classes.Interfaces;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.APIResponses.LoginResponse;
import com.example.projetintp2_android.Classes.Objects.Prescriptions;

import java.util.List;


import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceAPI_V2 {

    @POST("{locale}/register")
    @FormUrlEncoded
    Call<APIResponse> register(@Path("locale") String locale,
                               @Field("name") String nom,
                               @Field("email") String email,
                               @Field("password") String psw,
                               @Field("password_confirmation") String pswd
    );

    @POST("{locale}/login")
    @FormUrlEncoded
    Call<APIResponse> login(@Path("locale") String locale,
                            @Field("email") String nom,
                            @Field("password") String psw);

    @POST("{locale}/logout")
    Call<APIResponse> logout(@Path("locale") String locale,
                             @Header("Authorization") String token);

    @POST("{locale}/update")
    @FormUrlEncoded
    Call<APIResponse> update(@Path("locale") String locale,
                             @Header("Authorization") String token,
                             @Field("name") String nom,
                             @Field("email") String email,
                             @Field("password") String psw);

    @PATCH("{locale}/updateName")
    @FormUrlEncoded
    Call<APIResponse> updateName(@Path("locale") String locale,
                                 @Header("Authorization") String token,
                                 @Field("name") String nom);

    @PATCH("{locale}/updateEmail")
    @FormUrlEncoded
    Call<APIResponse> updateEmail(@Path("locale") String locale,
                                  @Header("Authorization") String token,
                                  @Field("email") String email);

    @PATCH("{locale}/updatePassword")
    @FormUrlEncoded
    Call<APIResponse> updatePassword(@Path("locale") String locale,
                                     @Header("Authorization") String token,
                                     @Field("current_password") String current_password,
                                     @Field("password") String password,
                                     @Field("password_confirmation") String password_confirmation);

    @GET("{locale}/prescriptions")
    Call<List<Prescriptions>> getMedicaments(@Path("locale") String locale,
                                             @Header("Authorization") String token);
}
