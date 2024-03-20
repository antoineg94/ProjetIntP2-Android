package com.example.projetintp2_android.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity(tableName = "Table_Prescription")
public class Prescriptions {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    private int id;
    @SerializedName("nameOfPrescription")
    private String nameOfPrescription;
    @SerializedName("dateOfPrescription")
    private Date dateOfPrescription;
    @SerializedName("dateOfStart")
    private Date dateOfStart;
    @SerializedName("durationOfPrescriptionInDays")
    private int durationOfPrescriptionInDays;
    @SerializedName("firstIntakeHour")
    private LocalTime  firstIntakeHour;
    @SerializedName("frequencyBetweenDosesInHours")
    private int frequencyBetweenDosesInHours;
    @SerializedName("frequencyOfIntakeInDays")
    private int frequencyOfIntakeInDays;
    @SerializedName("frequencyPerDay")
    private int frequencyPerDay;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("medication_id")
    private int medication_id;
    public Prescriptions(int id, String nameOfPrescription, Date dateOfPrescription, Date dateOfStart,
                         int durationOfPrescriptionInDays, LocalTime  firstIntakeHour, int frequencyBetweenDosesInHours,
                         int frequencyOfIntakeInDays, int frequencyPerDay, int user_id, int medication_id) {
        this.id = id;
        this.nameOfPrescription = nameOfPrescription;
        this.dateOfPrescription = dateOfPrescription;
        this.dateOfStart = dateOfStart;
        this.durationOfPrescriptionInDays = durationOfPrescriptionInDays;
        this.firstIntakeHour = firstIntakeHour;
        this.frequencyBetweenDosesInHours = frequencyBetweenDosesInHours;
        this.frequencyOfIntakeInDays = frequencyOfIntakeInDays;
        this.frequencyPerDay = frequencyPerDay;
        this.user_id = user_id;
        this.medication_id = medication_id;
    }

    @NonNull
    public int getId() {
        return id;
    }
    public void setId(@NonNull int id) {
        this.id = id;
    }
    public String getNameOfPrescription() {
        return nameOfPrescription;
    }
    public void setNameOfPrescription(String nameOfPrescription) {
        this.nameOfPrescription = nameOfPrescription;
    }
    public Date getDateOfPrescription() {
        return dateOfPrescription;
    }
    public void setDateOfPrescription(Date dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }
    public Date getDateOfStart() {
        return dateOfStart;
    }
    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }
    public int getDurationOfPrescriptionInDays() {
        return durationOfPrescriptionInDays;
    }
    public void setDurationOfPrescriptionInDays(int durationOfPrescriptionInDays) {
        this.durationOfPrescriptionInDays = durationOfPrescriptionInDays;
    }
    public LocalTime getFirstIntakeHour() {
        return firstIntakeHour;
    }
    public void setFirstIntakeHour(LocalTime  firstIntakeHour) {
        this.firstIntakeHour = firstIntakeHour;
    }
    public int getFrequencyBetweenDosesInHours() {
        return frequencyBetweenDosesInHours;
    }
    public void setFrequencyBetweenDosesInHours(int frequencyBetweenDosesInHours) {
        this.frequencyBetweenDosesInHours = frequencyBetweenDosesInHours;
    }
    public int getFrequencyOfIntakeInDays() {

        return frequencyOfIntakeInDays;
    }
    public void setFrequencyOfIntakeInDays(int frequencyOfIntakeInDays) {
        this.frequencyOfIntakeInDays = frequencyOfIntakeInDays;
    }
    public int getFrequencyPerDay() {
        return frequencyPerDay;
    }
    public void setFrequencyPerDay(int frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getMedication_id() {
        return medication_id;
    }
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
}
