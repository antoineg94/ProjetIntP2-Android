package com.example.projetintp2_android.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetintp2_android.R;

public class LoginActivitytest extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword, textViewCreateAccount;

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
            }
        });
    }

    // methode qui verifie la conxion

    private void checkLogin()
        {
            email=editTextEmail.getText().toString();
            password=editTextPassword.getText().toString();
            if(email.isEmpty()| password.isEmpty()){
                alertFail("email et mot de passe requis.");
            }
            else {
                sendLogin();
            }

    }

    private void sendLogin() {
        Toast.makeText(this,"Envoyé",Toast.LENGTH_LONG).show();
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

