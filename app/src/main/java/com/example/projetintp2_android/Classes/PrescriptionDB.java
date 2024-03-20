package com.example.projetintp2_android.Classes;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Prescriptions.class}, version = 1)
@TypeConverters({CustomTypeConverters.class})
public abstract class PrescriptionDB extends RoomDatabase {
    public abstract PrescriptionDAO pdao();
}
