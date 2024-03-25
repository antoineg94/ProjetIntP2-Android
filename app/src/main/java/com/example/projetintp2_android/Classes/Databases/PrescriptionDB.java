package com.example.projetintp2_android.Classes.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.projetintp2_android.Classes.CustomTypeConverters;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Objects.Prescription;

@Database(entities = {Prescription.class}, version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class PrescriptionDB extends RoomDatabase {
    public abstract PrescriptionDAO pdao();
}
