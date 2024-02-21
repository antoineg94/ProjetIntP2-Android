package com.example.projetintp2_android.Classes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceServeur {
    @GET("/medicament.php")
    Call<Medicaments> getMedicaments();

    @GET("/getListeMedicaments.php")
    Call<List<Medicaments>> getListeMedicaments();

    @GET("/prescription.php")
    Call<Prescriptions> getPrescriptions();

    @GET("/getListePrescriptions.php")
    Call<List<Prescriptions>> getListePrescriptions();

}
