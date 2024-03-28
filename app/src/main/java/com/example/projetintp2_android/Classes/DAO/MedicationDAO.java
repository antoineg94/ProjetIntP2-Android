package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Medications;

import java.util.List;

@Dao
public interface MedicationDAO {

    @Query("SELECT * FROM Table_Medications")
    List<Medications> getAfficherM();

    @Query("SELECT * FROM Table_Medications WHERE id = :id")
    Medications getMedicationById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedication(Medications medication);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMedications(List<Medications> medications);

}
