package com.example.projetintp2_android.Classes.Objects;

import com.google.gson.annotations.SerializedName;

public class Medicaments {

    @SerializedName("id")
    int id;
    @SerializedName("nom")
    String nom;
    @SerializedName("fonction")
    String fonction;

    public Medicaments(int id, String nom, String fonction) {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
}
