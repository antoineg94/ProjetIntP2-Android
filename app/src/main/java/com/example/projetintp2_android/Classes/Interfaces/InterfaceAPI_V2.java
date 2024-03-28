package com.example.projetintp2_android.Classes.Interfaces;

import androidx.room.Update;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.DELETE;
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

    @POST("{locale}/prescriptions")
    @FormUrlEncoded
    Call<APIResponse> postPrescriptions(@Path("locale") String locale,
                                        @Header("Authorization") String token,
                                        @Field("nameOfPrescription") String nameOfPrescription,
                                        @Field("dateOfPrescription") Date dateOfPrescription,
                                        @Field("dateOfStart") Date dateOfStart,
                                        @Field("durationOfPrescriptionInDays") int durationOfPrescriptionInDays,
                                        @Field("frequencyBetweenDosesInHours") int frequencyBetweenDosesInHours,
                                        @Field("frequencyOfIntakeInDays") int frequencyOfIntakeInDays,
                                        @Field("firstIntakeHour") LocalTime firstIntakeHour,
                                        @Field("medication_id") int medication_id);

    @DELETE("{locale}/prescriptions/{id}")
    Call<APIResponse> deletePrescriptions(@Path("locale") String locale,
                                          @Header("Authorization") String token,
                                          @Path("id") int id);

    @GET("{locale}/medications")
    Call<APIResponse> getMedications(@Path("locale") String locale,
                                     @Header("Authorization") String token);

    @GET("{locale}/alerts")
    Call<APIResponse> getAlerts(@Path("locale") String locale,
                                @Header("Authorization") String token);

    @POST("{locale}/alerts")
    @FormUrlEncoded
    Call<APIResponse> updateAlerts(@Path("locale") String locale,
                                   @Header("Authorization") String token,
                                   @Field("isMedicationTaken") boolean isMedicationTaken
    );

    @GET("{locale}/calendars")
    Call<APIResponse> getCalendars(@Path("locale") String locale,
                                   @Header("Authorization") String token);

    @GET("{locale}/devices")
    Call<APIResponse> getDevices(@Path("locale") String locale,
                                 @Header("Authorization") String token);

    @POST("{locale}/devices")
    @FormUrlEncoded
    Call<APIResponse> postDevices(@Path("locale") String locale,
                                  @Header("Authorization") String token,
                                  @Field("noSerie") String noSerie,
                                  @Field("associatedPatientFullName") String associatedPatientFullName);

    @POST("{locale}/devices")
    @FormUrlEncoded
    Call<APIResponse> updateDevices(@Path("locale") String locale,
                                    @Header("Authorization") String token,
                                    @Field("associatedPatientFullName") String associatedPatientFullName

    );

    @GET("{locale}/logs")
    Call<APIResponse> getLogs(@Path("locale") String locale,
                              @Header("Authorization") String token);

}
