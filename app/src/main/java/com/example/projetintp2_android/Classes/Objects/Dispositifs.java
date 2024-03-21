package com.example.projetintp2_android.Classes.Objects;

import com.google.gson.annotations.SerializedName;

public class Dispositifs {

    @SerializedName("id")
    int id;

    @SerializedName("noSerie")
    String noSerie;

    @SerializedName("associatedPatientFullName")
    String associatedPatientFullName;
    @SerializedName("user_id")
    int user_id;

    public Dispositifs(int id, String noSerie, String associatedPatientFullName, int user_id) {
        this.id = id;
        this.noSerie = noSerie;
        this.associatedPatientFullName = associatedPatientFullName;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public String getAssociatedPatientFullName() {
        return associatedPatientFullName;
    }

    public void setAssociatedPatientFullName(String associatedPatientFullName) {
        this.associatedPatientFullName = associatedPatientFullName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
