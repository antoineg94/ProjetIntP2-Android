package Interfaces;

import com.example.projetintp2_android.Users;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceServeur {
    @POST("register")
    Call<ResponseBody> register(@Body Users request);

/*
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @GET("user")
    Call<UserResponse> getUser();

    @POST("logout")
    Call<LogoutResponse> logout();*/
}
