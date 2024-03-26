package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Devices;

import java.util.List;

@Dao
public interface DeviceDAO {

    @Query("SELECT * FROM Table_Devices")
    List<Devices> getAllDevices();

    @Query("SELECT * FROM Table_Devices WHERE id = :id")
    Devices getDeviceById(int id);

    @Insert
    void insertDevice(Devices device);

    @Insert
    void insertAllDevices(List<Devices> devices);
    @Update
    void updateDevice(Devices device);

    @Delete
    void deleteDevice(Devices device);


}
