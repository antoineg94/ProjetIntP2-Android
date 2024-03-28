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

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceServeur;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etNom,etPrenom,etEmail,etMotDePasse,etConfirmationMotDePasse;
    //Choix de la photo de profil a faire
    Button btCreationCompte,btChoixImage;
    String Nom,prenom,motPasse,email,confirmation,nomComplet;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewProfile;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle(R.string.creation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNom = findViewById(R.id.etNom);
        etPrenom = findViewById(R.id.etPrenom);
        etEmail = findViewById(R.id.etEmailmodifie);
        etMotDePasse = findViewById(R.id.etMotDePasse);
        btCreationCompte = findViewById(R.id.btCreationCompte);


        etConfirmationMotDePasse = findViewById(R.id.etConfirmationMotDePasse);

        btCreationCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegistry();
            }
        });
        

    }
    /*public void verifierPermission() {
        ActivityResultLauncher<String[]> permissionsLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        result.forEach((permission, reponse) -> {
                            if (reponse) {
                                openFileChooser();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Permission refusée", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        );
        String[] permissions = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
        };
        permissionsLauncher.launch(permissions);
    }*/


    //methode de validation des champs(vérifie si tous les champs obligatoires a la creation de compte sont bien rentrés)
    public void checkRegistry() {
            Nom = etNom.getText().toString();
            prenom = etPrenom.getText().toString();
            email = etEmail.getText().toString();
            motPasse = etMotDePasse.getText().toString();
            confirmation = etConfirmationMotDePasse.getText().toString();
            nomComplet=Nom + " "+ prenom;



            if(Nom.isEmpty()||prenom.isEmpty()||email.isEmpty()||motPasse.isEmpty())
            {
                alertFail (R.string.champs_creation_compte);
            }
            else if (!motPasse.equals(confirmation))
            {
                alertFail(R.string.mot_passe_identique);
            }
            else
            {
                sendRegister(nomComplet,email,motPasse,confirmation);
            }


    }

    public void sendRegister(String name,String courriel,String password,String confirm) {

        InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);

       //Users  request = new Users( name, courriel,password);
        String locale = "en";

        Call<APIResponse> call = serveur.register(locale,name, courriel,password,confirm);

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse resultat= response.body();
                Intent intent = new Intent(RegisterActivity.this,LoginActivitytest.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"une erreur",Toast.LENGTH_LONG).show();


            }

        });



    }

/*
    private void alertSuccess(String s) {
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

    private void alertFail(int s) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.echec)
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