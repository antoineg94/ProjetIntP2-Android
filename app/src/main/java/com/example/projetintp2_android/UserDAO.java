package com.example.projetintp2_android;

import androidx.room.Dao;
import androidx.room.Update;

@Dao
public interface UserDAO {

    // mettre a jour un produit
    @Update
    public void miseAJour(Users user);

}
