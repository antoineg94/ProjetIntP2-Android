package com.example.projetintp2_android.Classes.Interfaces;

import com.example.projetintp2_android.Classes.APIResponses.LoginResponse;
import com.example.projetintp2_android.Classes.Objects.Prescriptions;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceAPI_V2 {

    @POST("{locale}/register")
    @FormUrlEncoded
    Call<ResponseBody> register(@Path ("locale") String locale,
                                @Field("name") String nom,
                                @Field("email") String email,
                                @Field("password") String psw );

    @POST("{locale}/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Path ("locale") String locale,
                              @Field("email") String nom,
                              @Field("password") String psw);

    @POST("{locale}/logout")
    Call<LoginResponse> logout(@Path("locale") String locale,
                               @Header("Authorization") String token);

    @POST("{locale}/update")
    @FormUrlEncoded
    Call<ResponseBody> update(@Path("locale") String locale,
                              @Header("Authorization") String token,
                              @Field("name") String nom,
                              @Field("email") String email,
                              @Field("password") String psw );

    @POST("{locale}/updateName")
    @FormUrlEncoded
    Call<ResponseBody> updateName(@Path("locale") String locale,
                                  @Header("Authorization") String token,
                                  @Field("name") String nom);

    @POST("{locale}/updateEmail")
    @FormUrlEncoded
    Call<ResponseBody> updateEmail(@Path("locale") String locale,
                                   @Header("Authorization") String token,
                                   @Field("email") String email);

    @POST("{locale}/updatePassword")
    @FormUrlEncoded
    Call<ResponseBody> updatePassword(@Path("locale") String locale,
                                      @Header("Authorization") String token,
                                      @Field("passord") String password);

    @GET("{locale}/prescriptions")
    Call<List<Prescriptions>> getMedicaments(@Path("locale") String locale,
                                             @Header("Authorization") String token);
}
