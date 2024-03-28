package com.example.projetintp2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText etEmail;
    String Email;
    Button btConfirmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmail= findViewById(R.id.etEmailForgotPassword);
        btConfirmer = findViewById(R.id.btResetPassword);

        btConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = etEmail.getText().toString().trim();
                boolean valide = true;
                if (Email.isEmpty()) {
                    etEmail.setError("Entrez votre email");
                    valide = false;
                }
                if(valide){
                    InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
                    String locale = "en";

                    Call<APIResponse> call = serveur.forgotPassword(locale,Email);
                    call.enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                            APIResponse resultat = response.body();
                            Log.d("forgotPassword", resultat.getMessage());
                            if (resultat.getStatus().equals("success")) {

                                Toast.makeText(ResetPasswordActivity.this, "email send", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Toast.makeText(ResetPasswordActivity.this, "une erreur", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}