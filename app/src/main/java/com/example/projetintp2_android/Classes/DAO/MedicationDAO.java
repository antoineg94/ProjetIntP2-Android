package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Medications;

import java.util.List;

public interface MedicationDAO {

    @Query("SELECT * FROM Table_Medications")
    List<Medications> getAfficherM();

    @Query("SELECT * FROM Table_Medications WHERE id = :id")
    Medications getMedicationById(int id);


}
