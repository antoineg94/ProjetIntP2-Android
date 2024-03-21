package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Calendars;

import java.util.List;

public interface CalendarDAO {

    @Query("SELECT * FROM Table_Calendars")
    List<Calendars> getAfficherC();
}
