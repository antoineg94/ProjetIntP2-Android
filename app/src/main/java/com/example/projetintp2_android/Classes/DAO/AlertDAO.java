package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Alerts;

import java.util.List;

@Dao
public  interface AlertDAO {

    @Query("SELECT * FROM Table_Alerts")
    List<Alerts> getAfficherA();

    @Query("SELECT * FROM Table_Alerts WHERE id = :id")
    Alerts getAlertById(int id);

    @Insert
    void insertAlert(Alerts alert);
    @Insert
    void insertAllAlerts(List<Alerts> alerts);

    @Update
    void updateAlert(Alerts alert);




}
