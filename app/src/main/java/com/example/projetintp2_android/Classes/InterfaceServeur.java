package com.example.projetintp2_android.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceServeur {


    @GET("fr/prescriptions")
    Call<List<Prescriptions>> getMedicaments();


}
