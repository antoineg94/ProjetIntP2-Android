package com.example.projetintp2_android.Classes.APIResponses;

import com.example.projetintp2_android.Classes.Objects.DataFromAPI;
import com.example.projetintp2_android.Classes.Objects.UserV2;

public class APIResponse {

    String status, message;
    DataFromAPI data;

    UserV2 user;


    public APIResponse(String status, String message, DataFromAPI data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public String toString() {
        return "APILoginResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
