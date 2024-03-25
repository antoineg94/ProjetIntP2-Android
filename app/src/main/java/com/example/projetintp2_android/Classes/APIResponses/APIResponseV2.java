package com.example.projetintp2_android.Classes.APIResponses;

import com.example.projetintp2_android.Classes.DataClass.Data;
import com.example.projetintp2_android.Classes.Objects.DataFromAPI;

public class APIResponseV2 {

    String status, message, code;
    Data data;



    public APIResponseV2(String status, String message, Data data, String code) {
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

    public Data getData() {
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
