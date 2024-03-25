package com.example.projetintp2_android.Classes.Objects;

import android.icu.util.LocaleData;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.projetintp2_android.Classes.CustomTypeConverters;
import com.google.gson.annotations.SerializedName;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity(tableName = "Table_Calendars")
@TypeConverters(CustomTypeConverters.class)
public class Calendars {
    @PrimaryKey(autoGenerate = false)
            @SerializedName("id")
    int id;
    @SerializedName("dateOfIntake")
    String dateOfIntake;
    @SerializedName("timeOfIntake")
    String timeOfIntake;
    @SerializedName("active")
    Boolean active;
    @SerializedName("prescription_id")
    int prescription_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public Calendars(int id, String dateOfIntake, String timeOfIntake, Boolean active, int prescription_id, String created_at, String updated_at) {
        this.id = id;
        this.dateOfIntake = dateOfIntake;
        this.timeOfIntake = timeOfIntake;
        this.active = active;
        this.prescription_id = prescription_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfIntake() {
        return dateOfIntake;
    }

    public void setDateOfIntake(String dateOfIntake) {
        this.dateOfIntake = dateOfIntake;
    }

    public String getTimeOfIntake() {
        return timeOfIntake;
    }

    public void setTimeOfIntake(String timeOfIntake) {
        this.timeOfIntake = timeOfIntake;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
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

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @Override
    public String toString() {
        return "Calendars{" +
                "id=" + id +
                ", dateOfIntake=" + dateOfIntake +
                ", timeOfIntake=" + timeOfIntake +
                ", active=" + active +
                ", prescription_id=" + prescription_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
