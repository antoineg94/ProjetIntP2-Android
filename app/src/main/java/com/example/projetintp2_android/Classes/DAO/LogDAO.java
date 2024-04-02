package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projetintp2_android.Classes.Objects.Logs;

import java.util.List;
@Dao
public interface LogDAO {
    @Query("SELECT * FROM Table_Logs")
    List<Logs> getAllLogs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLog(Logs log);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLogs(List<Logs> logs);

}
