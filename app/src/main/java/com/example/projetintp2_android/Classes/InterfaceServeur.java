package com.example.projetintp2_android.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceServeur {

    @GET("prescriptions")
//    @GET("/laravel/public/api/prescriptions")
//    @GET("/index.php?route=/sql&pos=0&db=laravel&table=prescriptions")
    Call<List<Prescriptions>> getMedicaments();


}
