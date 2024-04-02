package com.example.projetintp2_android.Classes.APIResponses;

import android.util.JsonReader;

import com.example.projetintp2_android.Classes.Objects.DataFromAPI;
import com.example.projetintp2_android.Classes.Objects.UserV2;

public class APIResponse {

    String status, message, code;
    DataFromAPI data;



    public APIResponse(String status, String message, DataFromAPI data, String code) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = code;
    }


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataFromAPI getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "APILoginResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
