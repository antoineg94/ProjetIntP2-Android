package com.example.projetintp2_android.Classes.Databases;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projetintp2_android.Classes.DAO.UserDAO;
import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.Objects.Users;

@Database(entities = {UserV2.class}, version = 2)
public  abstract class UsersDB extends RoomDatabase {

    public abstract UserDAO udao();
}
