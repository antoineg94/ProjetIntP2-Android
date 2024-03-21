package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Table_Medications")
public class Medications {

    @SerializedName("id")
            @PrimaryKey(autoGenerate = false)
    int id;
    @SerializedName("name")

    String name;
    @SerializedName("fonction")
    String fonction;
    @SerializedName("canBeInPillBox")
    boolean canBeInPillBox;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public Medications(int id, String nom, String fonction, boolean canBeInPillBox, String created_at, String updated_at) {
        this.id = id;
        this.name = nom;
        this.fonction = fonction;
        this.canBeInPillBox = canBeInPillBox;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    public boolean isCanBeInPillBox() {
        return canBeInPillBox;
    }

    public void setCanBeInPillBox(boolean canBeInPillBox) {
        this.canBeInPillBox = canBeInPillBox;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "Medicaments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fonction='" + fonction + '\'' +
                ", canBeInPillBox=" + canBeInPillBox +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
