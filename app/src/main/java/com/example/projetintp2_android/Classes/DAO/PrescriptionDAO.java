package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Prescriptions;

import java.util.List;

@Dao
public interface PrescriptionDAO {

    @Query("SELECT * FROM Table_Prescription")
    List<Prescriptions> getAfficherP();
    @Query("SELECT * FROM Table_Prescription WHERE id = :id")
    Prescriptions getPrescriptionById(int id);
    @Insert
    void insertPrescription(Prescriptions prescription);
    @Insert
    void insertAllPrescriptions(List<Prescriptions> prescriptions);
    @Update
    void updatePrescription(Prescriptions prescription);

    @Delete
    void deletePrescription(Prescriptions prescription);


}
