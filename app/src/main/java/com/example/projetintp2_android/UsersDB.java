package com.example.projetintp2_android;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1)
public  abstract class UsersDB extends RoomDatabase {

    public abstract UserDAO udao();
}
