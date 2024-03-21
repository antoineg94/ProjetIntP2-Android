package com.example.projetintp2_android.Classes.Objects;

import java.util.List;

public class DataFromAPI {

    UserV2 user;
    String token;
    List<Prescriptions> prescriptionsList;
    List<Medicaments> medicamentsList;
    List<Alerts> alertsList;
    List<Logs> logsList;

    public DataFromAPI(UserV2 user, String token, List<Prescriptions> prescriptionsList, List<Medicaments> medicamentsList, List<Alerts> alertsList, List<Logs> logsList) {
        this.user = user;
        this.token = token;
        this.prescriptionsList = prescriptionsList;
        this.medicamentsList = medicamentsList;
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

    public List<Medicaments> getMedicamentsList() {
        return medicamentsList;
    }

    public void setMedicamentsList(List<Medicaments> medicamentsList) {
        this.medicamentsList = medicamentsList;
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
                ", medicamentsList=" + medicamentsList +
                ", alertsList=" + alertsList +
                ", logsList=" + logsList +
                '}';
    }
}
