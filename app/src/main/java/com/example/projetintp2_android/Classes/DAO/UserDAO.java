package com.example.projetintp2_android.Classes.DAO;

import androidx.room.Dao;
import androidx.room.Update;

import com.example.projetintp2_android.Classes.Objects.Users;

@Dao
public interface UserDAO {

    // mettre a jour un produit
    @Update
    public void miseAJour(Users user);

}
