package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Alerts;

import java.util.List;

public  interface AlertDAO {

    @Query("SELECT * FROM Table_Alerts")
    List<Alerts> getAfficherA();

    @Query("SELECT * FROM Table_Alerts WHERE id = :id")
    Alerts getAlertById(int id);



}
