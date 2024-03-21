package com.example.projetintp2_android.Classes.Databases;

import androidx.room.Database;

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
import com.example.projetintp2_android.Classes.Objects.Prescriptions;
import com.example.projetintp2_android.Classes.Objects.Devices;

@Database(entities = { Medications.class, Prescriptions.class, Devices.class, Logs.class, Calendars.class, Alerts.class  }, version = 1)
public abstract class MainDB extends androidx.room.RoomDatabase  {

    public abstract MedicationDAO mdao();
    public abstract PrescriptionDAO pdao();
    public abstract DeviceDAO ddao();
    public abstract LogDAO ldao();
    public abstract CalendarDAO cdao();
    public abstract AlertDAO adao();
    

}
