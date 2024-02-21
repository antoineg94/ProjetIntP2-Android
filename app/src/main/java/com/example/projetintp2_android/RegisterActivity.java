package com.example.projetintp2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText etNom,etPrenom,etEmail,etMotDePasse;
    //Choix de la photo de profil a faire
    Button btCreationCompte;
    String Nom,prenom,motPasse,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle(("Création de compte"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmail);
        etMotDePasse = findViewById(R.id.etMotDePasse);
        btCreationCompte = findViewById(R.id.btCreationCompte);

        btCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegistry();
            }
        });
    }

    private void checkRegistry() {
            Nom = etNom.getText().toString();
            prenom = etPrenom.getText().toString();
            email = etEmail.getText().toString();
            motPasse = etMotDePasse.getText().toString();

            if(Nom.isEmpty()||prenom.isEmpty()||email.isEmpty()||motPasse.isEmpty())
            {
                alertFail ("les champs nom,prenom,email et mot de passe sont requis pour la création de compte");
            }


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