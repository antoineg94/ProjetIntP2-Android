package com.example.projetintp2_android.Classes.Objects;

import java.util.List;

public class DataFromAPI {

    UserV2 user;
    String token;
    Prescription prescription;
    List<Prescription> prescriptions;
    List<Medications> medications;
    List<Alerts> alerts;
    List<Logs> logs;
    List<Calendars> calendars;
    List<Devices> devices;


    public DataFromAPI(UserV2 user, String token, Prescription prescription, List<Prescription> prescriptions, List<Medications> medications, List<Alerts> alerts, List<Logs> logs, List<Calendars> calendars, List<Devices> devices) {
        this.user = user;
        this.token = token;
        this.prescription = prescription;
        this.prescriptions = prescriptions;
        this.medications = medications;
        this.alerts = alerts;
        this.logs = logs;
        this.calendars = calendars;
        this.devices = devices;
    }

    public UserV2 getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Medications> getMedications() {
        return medications;
    }

    public List<Alerts> getAlerts() {
        return alerts;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public List<Calendars> getCalendars() {
        return calendars;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    @Override
    public String toString() {
        return "DataFromAPI{" +
                "user=" + user +
                ", token=" + token +
                ", prescriptionsList=" + prescriptions +
                ", medicationsList=" + medications +
                ", alertsList=" + alerts +
                ", logsList=" + logs +
                '}';
    }
}
