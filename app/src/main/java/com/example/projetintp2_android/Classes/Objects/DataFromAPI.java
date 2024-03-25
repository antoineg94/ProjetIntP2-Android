package com.example.projetintp2_android.Classes.Objects;

import java.util.List;

public class DataFromAPI {

    UserV2 user;
    String token;
    List<Prescriptions> prescriptions;
    List<Medications> medications;
    List<Alerts> alerts;
    List<Logs> logs;
    List<Calendars> calendars;
    List<Devices> devices;


    public DataFromAPI(UserV2 user, String token, List<Prescriptions> prescriptions, List<Medications> medications, List<Alerts> alerts, List<Logs> logs, List<Calendars> calendars, List<Devices> devices) {
        this.user = user;
        this.token = token;
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

    public void setUser(UserV2 user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Prescriptions> getPrescriptionsList() {
        return prescriptions;
    }

    public void setPrescriptionsList(List<Prescriptions> prescriptionsList) {
        this.prescriptions = prescriptionsList;
    }

    public List<Medications> getMedicationsList() {
        return medications;
    }

    public void setMedicationsList(List<Medications> medicationsList) {
        this.medications = medicationsList;
    }

    public List<Alerts> getAlertsList() {
        return alerts;
    }

    public void setAlertsList(List<Alerts> alertsList) {
        this.alerts = alertsList;
    }

    public List<Logs> getLogsList() {
        return logs;
    }

    public void setLogsList(List<Logs> logsList) {
        this.logs = logsList;
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
