package com.example.projetintp2_android.Classes;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    public static final String BASE_URL = "10.0.2.2";
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getInstance(){

        if(retrofit == null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
