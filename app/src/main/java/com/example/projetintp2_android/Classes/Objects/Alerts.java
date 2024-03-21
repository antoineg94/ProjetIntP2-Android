package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_Alerts")
public class Alerts {

    @PrimaryKey(autoGenerate = false)

    int id;
    @SerializedName("isTheMedicationTaken")
    Boolean isTheMedicationTaken;
    @SerializedName("calendar_id")
    int calendar_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public Alerts(int id, Boolean isTheMedicationTaken, int calendar_id, String created_at, String updated_at) {
        this.id = id;
        this.isTheMedicationTaken = isTheMedicationTaken;
        this.calendar_id = calendar_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getTheMedicationTaken() {
        return isTheMedicationTaken;
    }

    public void setTheMedicationTaken(Boolean theMedicationTaken) {
        isTheMedicationTaken = theMedicationTaken;
    }

    public int getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(int calendar_id) {
        this.calendar_id = calendar_id;
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
        return "Alerts{" +
                "id=" + id +
                ", isTheMedicationTaken=" + isTheMedicationTaken +
                ", calendar_id=" + calendar_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
