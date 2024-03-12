package com.example.projetintp2_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Interfaces.InterfaceServeur;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivitytest extends AppCompatActivity  {

    private EditText editTextEmail, editTextPassword;
    private CheckBox checkBoxRememberMe ;
    private Button buttonLogin;
    private TextView textViewForgotPassword, textViewCreateAccount;
    boolean isChecked;

    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintest);

        editTextEmail = findViewById(R.id.etEmail);
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

                Intent intent = new Intent(LoginActivitytest.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }
    protected void onStart()
    {
        super.onStart();
        if(isChecked=checkBoxRememberMe.isChecked()) {
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                Intent intent = new Intent(LoginActivitytest.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


                //if()
            }
        }
    }

    // methode qui verifie la conxion

    private void checkLogin()
        {
            email=editTextEmail.getText().toString().trim();
            password=editTextPassword.getText().toString().trim();
            if(email.isEmpty()| password.isEmpty()){
                alertFail("email et mot de passe requis.");
            }
            else {
                sendLogin();
            }

    }

    private void sendLogin() {

        InterfaceServeur serveur = RetrofitInstance.getInstance().create(InterfaceServeur.class);

        // Users  request = new Users( name, courriel,password);

        Call<LoginResponse> call = serveur.login(email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resultat= response.body();

                if(resultat.getToken()!= null)
                {

                    SharedPrefManager.getInstance(LoginActivitytest.this).saveUser(resultat.getUser());
                    Intent intent = new Intent(LoginActivitytest.this,ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    //save user

                    // proceed with the login
                  //  Toast.makeText(LoginActivitytest.this,resultat.getMessage(),Toast.LENGTH_LONG).show();
                }
                else {
                    alertFail("informations de connexion invalides");
                 //   Toast.makeText(LoginActivitytest.this,"connexion echouée",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivitytest.this,"une erreur",Toast.LENGTH_LONG).show();


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

