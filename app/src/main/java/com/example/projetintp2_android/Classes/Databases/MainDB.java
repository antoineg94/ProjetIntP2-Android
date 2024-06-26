package com.example.projetintp2_android.Classes.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import com.example.projetintp2_android.Classes.CustomTypeConverters;
import com.example.projetintp2_android.Classes.DAO.AlertDAO;
import com.example.projetintp2_android.Classes.DAO.CalendarDAO;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.DAO.LogDAO;
import com.example.projetintp2_android.Classes.DAO.MedicationDAO;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Objects.Alerts;
import com.example.projetintp2_android.Classes.Objects.Calendars;
import com.example.projetintp2_android.Classes.Objects.Devices;
import com.example.projetintp2_android.Classes.Objects.Logs;
import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.Classes.Objects.Prescription;

@Database(entities = { Medications.class, Prescription.class, Devices.class, Logs.class, Calendars.class, Alerts.class  }, version = 2, exportSchema = false)
@TypeConverters({CustomTypeConverters.class})
public abstract class MainDB extends androidx.room.RoomDatabase  {

    public abstract MedicationDAO mdao();
    public abstract PrescriptionDAO pdao();
    public abstract DeviceDAO ddao();
    public abstract LogDAO ldao();
    public abstract CalendarDAO cdao();
    public abstract AlertDAO adao();

    private static volatile MainDB INSTANCE;

    public static MainDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MainDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MainDB.class, "MainDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    

}
