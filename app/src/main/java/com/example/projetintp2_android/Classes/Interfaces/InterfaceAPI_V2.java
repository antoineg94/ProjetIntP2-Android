package com.example.projetintp2_android.Classes.Interfaces;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;


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
    Call<APIResponse> register(@Path("locale") String locale,
                               @Field("name") String nom,
                               @Field("email") String email,
                               @Field("password") String psw);

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

    @POST("{locale}/updateName")
    @FormUrlEncoded
    Call<APIResponse> updateName(@Path("locale") String locale,
                                 @Header("Authorization") String token,
                                 @Field("name") String nom);

    @POST("{locale}/updateEmail")
    @FormUrlEncoded
    Call<APIResponse> updateEmail(@Path("locale") String locale,
                                  @Header("Authorization") String token,
                                  @Field("email") String email);

    @POST("{locale}/updatePassword")
    @FormUrlEncoded
    Call<APIResponse> updatePassword(@Path("locale") String locale,
                                     @Header("Authorization") String token,
                                     @Field("passord") String password);

    @GET("{locale}/prescriptions")
    Call<APIResponse> getPrescriptions(@Path("locale") String locale,
                                       @Header("Authorization") String token);

    @GET("{locale}/prescriptions")
    Call<ResponseBody> getPrescriptions2(@Path("locale") String locale,
                                         @Header("Authorization") String token);
    @POST("{locale}/prescriptions")
    @FormUrlEncoded
    Call<APIResponse> postPrescriptions(@Path("locale") String locale,
                                        @Header("Authorization") String token,
                                        @Field("name") String name,
                                        @Field("date") String date,
                                        @Field("start") String start,
                                        @Field("duration") int duration,
                                        @Field("firstIntakeHour") String firstIntakeHour,
                                        @Field("frequencyBetweenDosesInHours") int frequencyBetweenDosesInHours,
                                        @Field("frequencyOfIntakeInDays") int frequencyOfIntakeInDays,
                                        @Field("frequencyPerDay") int frequencyPerDay);
}
