package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_Devices")
public class Devices {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    int id;
    @SerializedName("noSerie")
    String noSerie;
    @SerializedName("associatedPatientFullName")
    String associatedPatientFullName;
    @SerializedName("user_id")
    int user_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public Devices(int id, String noSerie, String associatedPatientFullName, int user_id, String created_at, String updated_at) {
        this.id = id;
        this.noSerie = noSerie;
        this.associatedPatientFullName = associatedPatientFullName;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public String getAssociatedPatientFullName() {
        return associatedPatientFullName;
    }

    public void setAssociatedPatientFullName(String associatedPatientFullName) {
        this.associatedPatientFullName = associatedPatientFullName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "Devices{" +
                "id=" + id +
                ", noSerie='" + noSerie + '\'' +
                ", associatedPatientFullName='" + associatedPatientFullName + '\'' +
                ", user_id=" + user_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
