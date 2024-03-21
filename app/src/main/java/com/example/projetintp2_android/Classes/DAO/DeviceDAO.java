package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Devices;

import java.util.List;

public interface DeviceDAO {

    @Query("SELECT * FROM Table_Devices")
    List<Devices> getAfficherD();
}
