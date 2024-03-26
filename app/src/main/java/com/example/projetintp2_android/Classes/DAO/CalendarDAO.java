package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Calendars;

import java.util.List;
@Dao
public interface CalendarDAO {

    @Query("SELECT * FROM Table_Calendars")
    List<Calendars> getAfficherC();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCalendar(Calendars calendar);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCalendars(List<Calendars> calendars);
    @Update
    void updateCalendar(Calendars calendar);

}
