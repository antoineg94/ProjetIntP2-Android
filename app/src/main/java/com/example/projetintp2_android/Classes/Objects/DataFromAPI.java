package com.example.projetintp2_android.Classes.Objects;

import java.util.List;

public class DataFromAPI {

    UserV2 user;
    String token;
    Prescription prescription;
    List<Prescription> prescriptions;
    List<Medications> medications;
    Alerts alert;
    List<Alerts> alerts;
    List<Logs> logs;
    List<Calendars> calendars;
    Devices device;
    List<Devices> devices;

    public DataFromAPI(UserV2 user, String token, Prescription prescription, List<Prescription> prescriptions, List<Medications> medications, Alerts alert, List<Alerts> alerts, List<Logs> logs, List<Calendars> calendars, Devices device, List<Devices> devices) {

        this.user = user;
        this.token = token;
        this.prescription = prescription;
        this.prescriptions = prescriptions;
        this.medications = medications;
        this.alert = alert;
        this.alerts = alerts;
        this.logs = logs;
        this.calendars = calendars;
        this.device = device;
        this.devices = devices;
    }

    public UserV2 getUser() {
        return user;
    }

    public String getToken() {
        return token;
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

    public Prescription getPrescription() {
        return prescription;
    }

    public Devices getDevice() {
        return device;
    }

    public Alerts getAlert() {
        return alert;
    }

    @Override
    public String toString() {
        return "DataFromAPI{" +
                "user=" + user +
                ", token='" + token + '\'' +
                ", prescription=" + prescription +
                ", prescriptions=" + prescriptions +
                ", medications=" + medications +
                ", alerts=" + alerts +
                ", logs=" + logs +
                ", calendars=" + calendars +
                ", device=" + device +
                ", devices=" + devices +
                '}';
    }
}
