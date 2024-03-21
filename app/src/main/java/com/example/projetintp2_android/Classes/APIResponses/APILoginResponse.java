package com.example.projetintp2_android.Classes.APIResponses;

import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.Objects.Users;
import com.google.gson.JsonObject;

public class APILoginResponse {

    String status, message;
    JsonObject data;

    UserV2 user;


    public APILoginResponse(String status, String message, JsonObject data) {
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

    public JsonObject getUser() {
        return data;
    }

    public UserV2 getUserV2() {
        return user;
    }

    public UserV2 getUserV2FromJson() {
        user = new UserV2(data.get("id").getAsInt(),
                data.get("name").getAsString(),
                data.get("email").getAsString(),
                data.get("token").getAsString(),
                data.get("password").getAsString(),
                data.get("email_verified_at").getAsString(),
                data.get("created_at").getAsString(),
                data.get("updated_at").getAsString());
        return user;

    }


    public String toString() {
        return "APILoginResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
