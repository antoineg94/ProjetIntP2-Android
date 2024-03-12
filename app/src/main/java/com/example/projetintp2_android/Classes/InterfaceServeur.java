package com.example.projetintp2_android.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceServeur {

    @GET("/laravel/public/api/prescriptions")
    Call<Medicaments> getMedicaments();


}
