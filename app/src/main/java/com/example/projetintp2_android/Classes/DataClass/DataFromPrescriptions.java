package com.example.projetintp2_android.Classes.DataClass;

import com.example.projetintp2_android.Classes.Objects.Prescription;

import java.util.List;

public class DataFromPrescriptions extends Data{

    List<Prescription> prescriptions;

    public DataFromPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getPrescriptionsList() {
        return prescriptions;
    }

    public void setPrescriptionsList(List<Prescription> prescriptionsList) {
        this.prescriptions = prescriptionsList;
    }

    @Override
    public String toString() {
        return "DataFromPrescriptions{" +
                "prescriptions=" + prescriptions +
                '}';
    }

    @Override
    public Data getData() {
        return this;
    }


}
