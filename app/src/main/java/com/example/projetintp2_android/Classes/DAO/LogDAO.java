package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Logs;

import java.util.List;

public interface LogDAO {
    @Query("SELECT * FROM Table_Logs")
    List<Logs> getAfficherL();

}
