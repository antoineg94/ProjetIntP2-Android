package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Prescription;

import java.util.List;

@Dao
public interface PrescriptionDAO {

    @Query("SELECT * FROM Table_Prescriptions")
    List<Prescription> getAllPrescriptions();
    @Query("SELECT * FROM Table_Prescriptions WHERE id = :id")
    Prescription getPrescriptionById(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPrescription(Prescription prescription);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPrescriptions(List<Prescription> prescriptions);
    @Update
    void updatePrescription(Prescription prescription);

    @Delete
    void deletePrescription(Prescription prescription);


}
