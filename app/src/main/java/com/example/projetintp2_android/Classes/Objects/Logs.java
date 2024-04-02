package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.projetintp2_android.Classes.CustomTypeConverters;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_Logs")
@TypeConverters(CustomTypeConverters.class)
public class Logs {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    int id;
    @SerializedName("actionTimestamp")
    String actionTimestamp;
    @SerializedName("action")
    String action;
    @SerializedName("device_id")
    int device_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;

    public Logs(int id, String actionTimestamp, String action, int device_id, String created_at, String updated_at) {
        this.id = id;
        this.actionTimestamp = actionTimestamp;
        this.action = action;
        this.device_id = device_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(String actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", actionTimestamp='" + actionTimestamp + '\'' +
                ", action='" + action + '\'' +
                ", device_id=" + device_id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
