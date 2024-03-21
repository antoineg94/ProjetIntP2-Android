package com.example.projetintp2_android.Classes.Objects;

import java.util.List;

public class DataFromAPI {

    UserV2 user;
    String token;
    List<Prescriptions> prescriptionsList;
    List<Medications> medicationsList;
    List<Alerts> alertsList;
    List<Logs> logsList;

    public DataFromAPI(UserV2 user, String token, List<Prescriptions> prescriptionsList, List<Medications> medicationsList, List<Alerts> alertsList, List<Logs> logsList) {
        this.user = user;
        this.token = token;
        this.prescriptionsList = prescriptionsList;
        this.medicationsList = medicationsList;
        this.alertsList = alertsList;
        this.logsList = logsList;
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
        return prescriptionsList;
    }

    public void setPrescriptionsList(List<Prescriptions> prescriptionsList) {
        this.prescriptionsList = prescriptionsList;
    }

    public List<Medications> getMedicationsList() {
        return medicationsList;
    }

    public void setMedicationsList(List<Medications> medicationsList) {
        this.medicationsList = medicationsList;
    }

    public List<Alerts> getAlertsList() {
        return alertsList;
    }

    public void setAlertsList(List<Alerts> alertsList) {
        this.alertsList = alertsList;
    }

    public List<Logs> getLogsList() {
        return logsList;
    }

    public void setLogsList(List<Logs> logsList) {
        this.logsList = logsList;
    }

    @Override
    public String toString() {
        return "DataFromAPI{" +
                "user=" + user +
                ", token=" + token +
                ", prescriptionsList=" + prescriptionsList +
                ", medicationsList=" + medicationsList +
                ", alertsList=" + alertsList +
                ", logsList=" + logsList +
                '}';
    }
}
