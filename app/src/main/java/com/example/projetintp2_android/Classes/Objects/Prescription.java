package com.example.projetintp2_android.Classes.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_Prescriptions")
public class Prescription {


    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("id")
    private int id;
    @SerializedName("nameOfPrescription")
    private String nameOfPrescription;
    @SerializedName("dateOfPrescription")
    private String dateOfPrescription;
    @SerializedName("dateOfStart")
    private String dateOfStart;
    @SerializedName("durationOfPrescriptionInDays")
    private int durationOfPrescriptionInDays;
    @SerializedName("firstIntakeHour")
    private String firstIntakeHour;
    @SerializedName("frequencyBetweenDosesInHours")
    private int frequencyBetweenDosesInHours;
    @SerializedName("frequencyOfIntakeInDays")
    private int frequencyOfIntakeInDays;
    @SerializedName("frequencyPerDay")
    private int frequencyPerDay;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("medication_id")
    private final int medication_id;
    @SerializedName("created_at")
    private final String created_at;
    @SerializedName("updated_at")
    private final String updated_at;

    public Prescription(int id, String nameOfPrescription, String dateOfPrescription, String dateOfStart,
                        int durationOfPrescriptionInDays, String firstIntakeHour, int frequencyBetweenDosesInHours, int frequencyOfIntakeInDays,
                        int frequencyPerDay, int user_id, int medication_id, String created_at, String updated_at) {
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
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getDateOfPrescription() {
        return dateOfPrescription;
    }

    public void setDateOfPrescription(String dateOfPrescription) {
        this.dateOfPrescription = dateOfPrescription;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public int getDurationOfPrescriptionInDays() {
        return durationOfPrescriptionInDays;
    }

    public void setDurationOfPrescriptionInDays(int durationOfPrescriptionInDays) {
        this.durationOfPrescriptionInDays = durationOfPrescriptionInDays;
    }

    public String getFirstIntakeHour() {
        return firstIntakeHour;
    }

    public void setFirstIntakeHour(String firstIntakeHour) {
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

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "medication_id=" + medication_id +
                ", id=" + id +
                ", nameOfPrescription='" + nameOfPrescription + '\'' +
                ", dateOfPrescription='" + dateOfPrescription + '\'' +
                ", dateOfStart='" + dateOfStart + '\'' +
                ", durationOfPrescriptionInDays=" + durationOfPrescriptionInDays +
                ", firstIntakeHour='" + firstIntakeHour + '\'' +
                ", frequencyBetweenDosesInHours=" + frequencyBetweenDosesInHours +
                ", frequencyOfIntakeInDays=" + frequencyOfIntakeInDays +
                ", frequencyPerDay=" + frequencyPerDay +
                ", user_id=" + user_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
