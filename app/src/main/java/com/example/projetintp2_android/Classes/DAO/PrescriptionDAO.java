package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Prescriptions;

import java.util.List;

@Dao
public interface PrescriptionDAO {

    @Query("SELECT * FROM Table_Prescription")
    List<Prescriptions> getAfficherP();


}
