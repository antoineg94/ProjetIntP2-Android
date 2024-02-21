package com.example.projetintp2_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    EditText etNom,etPrenom,etEmail,etMotDePasse,etConfirmationMotDePasse;
    //Choix de la photo de profil a faire
    Button btCreationCompte,btChoixImage;
    String Nom,prenom,motPasse,email,confirmation;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Uri imageUri;
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
        btChoixImage = findViewById(R.id.btChoixImage);
        imageViewProfile = findViewById(R.id.ivImageProfil);
        etConfirmationMotDePasse = findViewById(R.id.etConfirmationMotDePasse);

        btCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegistry();
            }
        });
        
        btChoixImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // methode pour aller choisir la photo de profil dans le telephone
                openFileChooser();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Glide.with(this).load(bitmap).into(imageViewProfile);
            }
            catch ( IOException e) {
                e.printStackTrace();
            }
        }
    }

    //methode de validation des champs(vérifie si tous les champs obligatoires a la creation de compte sont bien rentrés)
    private void checkRegistry() {
            Nom = etNom.getText().toString();
            prenom = etPrenom.getText().toString();
            email = etEmail.getText().toString();
            motPasse = etMotDePasse.getText().toString();
            confirmation = etConfirmationMotDePasse.getText().toString();


            if(Nom.isEmpty()||prenom.isEmpty()||email.isEmpty()||motPasse.isEmpty())
            {
                alertFail ("les champs nom,prenom,email et mot de passe sont requis pour la création de compte");
            }
            else if (!motPasse.equals(confirmation))
            {
                alertFail("les mots de passes doivent etre identiques");
            }
            else
            {
                sendRegister();
            }


    }

    private void sendRegister() {
        Toast.makeText(this,"creation de compte réussie",Toast.LENGTH_LONG).show();
    }


    /*private void alertSuccess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("succes")
                .setIcon(R.drawable.ic_check)
                .setMessage(s)
                .setPositiveButton("connecté", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     //   onBackPressed();
                     //   Toast.makeText(this,"Envoyé",Toast.LENGTH_LONG).show();
                    }
                });
    }*/

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