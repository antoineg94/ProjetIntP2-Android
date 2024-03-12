package Interfaces;

import com.example.projetintp2_android.LoginResponse;
import com.example.projetintp2_android.Users;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceServeur {
    @POST("/projet/laravel/public/api/register")
    @FormUrlEncoded
    Call<ResponseBody> register(@Field("name") String nom,
                                @Field("email") String email,
                                @Field("password") String psw );


    @POST("/projet/laravel/public/api/login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("email") String nom,
                              @Field("password") String psw);
    @POST("/projet/laravel/public/api/logout")
    Call<ResponseBody> logout();
  //  @POST("register")
  //  Call<RegisterResponse> register(@Body RegisterRequest request);

 //   @GET("alluser")
  //  Call<ResponseBody> getUser();

}
