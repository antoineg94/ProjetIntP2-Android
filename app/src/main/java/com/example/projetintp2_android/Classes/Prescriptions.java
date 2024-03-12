package com.example.projetintp2_android.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Time;

public class Prescriptions {

    @SerializedName("id")
    int id;
    @SerializedName("nameOfPrescription")
    String nameOfPrescription;
    @SerializedName("dateOfPrescription")
    Date dateOfPrescription;
    @SerializedName("dateOfStart")
    Date dateOfStart;
    @SerializedName("durationOffPrescriptionInDays")
    int durationOffPrescriptionInDays;
    @SerializedName("firstIntakeHour")
    Time firstIntakeHour;
    @SerializedName("frequencyBetweenDosesInHours")
    int frequencyBetweenDosesInHours;
    @SerializedName("frequencyPerDay")
    int frequencyPerDay;
    @SerializedName("user_id")
    int user_id;
    @SerializedName("medication_id")
    int medication_id;
    public Prescriptions(int id, String nameOfPrescription, Date dateOfPrescription, Date dateOfStart,
                         int durationOffPrescriptionInDays, Time firstIntakeHour, int frequencyBetweenDosesInHours,
                         int frequencyPerDay, int user_id, int medication_id) {
        this.id = id;
        this.nameOfPrescription = nameOfPrescription;
        this.dateOfPrescription = dateOfPrescription;
        this.dateOfStart = dateOfStart;
        this.durationOffPrescriptionInDays = durationOffPrescriptionInDays;
        this.firstIntakeHour = firstIntakeHour;
        this.frequencyBetweenDosesInHours = frequencyBetweenDosesInHours;
        this.frequencyPerDay = frequencyPerDay;
        this.user_id = user_id;
        this.medication_id = medication_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getDurationOffPrescriptionInDays() {
        return durationOffPrescriptionInDays;
    }

    public void setDurationOffPrescriptionInDays(int durationOffPrescriptionInDays) {
        this.durationOffPrescriptionInDays = durationOffPrescriptionInDays;
    }

    public Time getFirstIntakeHour() {
        return firstIntakeHour;
    }

    public void setFirstIntakeHour(Time firstIntakeHour) {
        this.firstIntakeHour = firstIntakeHour;
    }

    public int getFrequencyBetweenDosesInHours() {
        return frequencyBetweenDosesInHours;
    }

    public void setFrequencyBetweenDosesInHours(int frequencyBetweenDosesInHours) {
        this.frequencyBetweenDosesInHours = frequencyBetweenDosesInHours;
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

    public void setMedication_id(int medication_id) {
        this.medication_id = medication_id;
    }
}
