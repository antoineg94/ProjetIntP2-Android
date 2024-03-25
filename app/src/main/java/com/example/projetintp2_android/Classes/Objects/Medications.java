package com.example.projetintp2_android.Classes.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.projetintp2_android.Classes.CustomTypeConverters;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Table_Medications")
@TypeConverters(CustomTypeConverters.class)
public class Medications {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("function")
    private String function;
    @SerializedName("canBeInPillBox")
    private boolean canBeInPillBox;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public Medications(int id, String name, String function, boolean canBeInPillBox, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.function = function;
        this.canBeInPillBox = canBeInPillBox;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanBeInPillBox() {
        return canBeInPillBox;
    }

    public void setCanBeInPillBox(boolean canBeInPillBox) {
        this.canBeInPillBox = canBeInPillBox;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Medicaments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", function='" + function + '\'' +
                ", canBeInPillBox=" + canBeInPillBox +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
