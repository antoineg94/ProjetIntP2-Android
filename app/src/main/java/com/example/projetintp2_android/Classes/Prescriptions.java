package com.example.projetintp2_android.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Prescriptions {

    @SerializedName("id")
    private int id;
    @SerializedName("nameOfPrescription")
    private String nameOfPrescription;
    @SerializedName("dateOfPrescription")
    private Date dateOfPrescription;
    @SerializedName("dateOfStart")
    private Date dateOfStart;
    @SerializedName("durationOffPrescriptionInDays")
    private int durationOffPrescriptionInDays;
    @SerializedName("firstIntakeHour")
    private String firstIntakeHour;
    @SerializedName("frequencyBetweenDosesInHours")
    private int frequencyBetweenDosesInHours;
    @SerializedName("frequencyPerDay")
    private int frequencyPerDay;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("medication_id")
    private int medication_id;

    public Prescriptions(int id, String nameOfPrescription, Date dateOfPrescription, Date dateOfStart,
                         int durationOffPrescriptionInDays, String firstIntakeHour, int frequencyBetweenDosesInHours,
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
