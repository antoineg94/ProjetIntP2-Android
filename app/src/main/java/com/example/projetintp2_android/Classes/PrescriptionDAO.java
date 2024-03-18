package com.example.projetintp2_android.Classes;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PrescriptionDAO {

    @Query("SELECT * FROM Table_Prescription")
    List<Prescriptions> getAfficherP();


}
