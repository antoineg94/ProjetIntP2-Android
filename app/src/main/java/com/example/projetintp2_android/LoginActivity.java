package com.example.projetintp2_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;

import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    boolean isChecked;
    private EditText editTextEmail, editTextPassword;
    private CheckBox checkBoxRememberMe;
    private Button buttonLogin;
    private TextView textViewForgotPassword, textViewCreateAccount;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintest);

        editTextEmail = findViewById(R.id.etEmailmodifie);
        editTextPassword = findViewById(R.id.etMotDePasse);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewCreateAccount = findViewById(R.id.textViewCreateAccount);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
                // Code pour la vérification des informations de connexion et l'authentification
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page de réinitialisation du mot de passe
            }
        });

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page de création de compte

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    protected void onStart() {
        super.onStart();
        if (isChecked = checkBoxRememberMe.isChecked()) {
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


                //if()
            }
        }
    }

    // methode qui verifie la conxion

    private void checkLogin() {
        sendLogin();
  /*      email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        if (email.isEmpty() | password.isEmpty()) {
            alertFail("email et mot de passe requis.");
        } else {
            sendLogin();
        }*/

    }

    private void sendLogin() {

        InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);

        // Users  request = new Users( name, courriel,password);
        String locale = "en";
        email = "1234@1234";
        password = "123456789";
        Call<APIResponse> call = serveur.login(locale, email, password);

        call.enqueue(new Callback<APIResponse>() {

            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse loginResponse = response.body();

                try {

                    UserV2 user = loginResponse.getData().getUser();
                    String token = loginResponse.getData().getToken();

                    Log.d("user", user.toString());
                    Log.d("token", token);
                    SharedPrefManager.getInstance(LoginActivity.this).SaveUserV2(user);
                    SharedPrefManager.getInstance(LoginActivity.this).saveToken(token);
                    Intent intent = new Intent(LoginActivity.this, GestionPrescriptionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    //Changer l'intent pour aller vers la page d'accueil(prescriptions)


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }

        });


        // Toast.makeText(this,"Envoyé",Toast.LENGTH_LONG).show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Echec")
                .setIcon(R.drawable.ic_loginwarning24)
                .setMessage(s)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


}

