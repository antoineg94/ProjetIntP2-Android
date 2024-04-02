package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceServeur;
import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.Objects.Users;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    TextView tvWelcome;
    ImageView ivLogout, ivEnregistrerNom, ivSupNom, ivSaveCourriel, ivSupCourriel;
    Button ivSavePassword, ivAnnulerPassword;
    ImageView ivModifNom, ivModifCourriel, ivModifMotPasse;
    TextView tvNomComplet, tvCourriel, tvPassword, nom, Courriel;
    EditText etNom, etCourriel, etMotdepasse, etAncienMotPasse, etNouveauMotPasse, etConfirmMotPasse;
    String Nom, courriel, motdepasse, confirmMotPasse, ancienMotPasse, token, locale;
    UserV2 user;
    private ImageView imageViewProfile;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_profile);
        getSupportActionBar().setTitle(R.string.profilemanagement);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoadIDRefs();
        LoadUserProfil();
        SetLocale("en");
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        ivModifNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// rend  invisible les textes
                tvNomComplet.setVisibility(View.INVISIBLE);
                nom.setVisibility(View.INVISIBLE);
                ivModifNom.setVisibility(View.INVISIBLE);
                // rend visible ce qui est cache
                etNom.setVisibility(View.VISIBLE);
                ivEnregistrerNom.setVisibility(View.VISIBLE);
                ivSupNom.setVisibility(View.VISIBLE);

                UserV2  user = SharedPrefManager.getInstance(ProfileActivity.this).getUserV2();
                etNom.setText(user.getName());
                //    etCourriel.setText(user.getEmail());
                //    etMotdepasse.setText("ppppppppp");

                ivEnregistrerNom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Nom = etNom.getText().toString().trim();
                        boolean valide = true;
                        if (Nom.isEmpty()) {
                            etNom.setError(getString(R.string.erreur_nom_complet));
                            valide = false;
                        }


                        if (valide) {
                            InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);

                            Log.d("token", "token" + token);
                            Log.d("locale", "locale" + locale);
                            Call<APIResponse> call = serveur.updateName(locale,"Bearer " + token,Nom);

                            call.enqueue(new Callback<APIResponse>() {
                                @Override
                                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                                    APIResponse resultat = response.body();
                                    Log.d("updateName", resultat.getMessage());
                                    if (resultat.getStatus().equals("success")) {
                                        SharedPrefManager.getInstance(ProfileActivity.this).updateName(Nom);
                                        UserV2  user = SharedPrefManager.getInstance(ProfileActivity.this).getUserV2();
                                        //  user.getName();
                                        tvNomComplet.setText(user.getName());
                                        tvNomComplet.setVisibility(View.VISIBLE);
                                        nom.setVisibility(View.VISIBLE);
                                        ivModifNom.setVisibility(View.VISIBLE);
                                        // rend visible ce qui est cache
                                        etNom.setVisibility(View.INVISIBLE);
                                        ivEnregistrerNom.setVisibility(View.INVISIBLE);
                                        ivSupNom.setVisibility(View.INVISIBLE);

                                    } else {
                                        alertFail(resultat.getMessage());
                                    }

                                }

                                @Override
                                public void onFailure(Call<APIResponse> call, Throwable t) {
                                    Toast.makeText(ProfileActivity.this, "une erreur", Toast.LENGTH_LONG).show();

                                }
                            });

                        }


                    }
                });

                ivSupNom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvNomComplet.setVisibility(View.VISIBLE);
                        nom.setVisibility(View.VISIBLE);
                        ivModifNom.setVisibility(View.VISIBLE);
                        // rend visible ce qui est cache
                        etNom.setVisibility(View.INVISIBLE);
                        ivEnregistrerNom.setVisibility(View.INVISIBLE);
                        ivSupNom.setVisibility(View.INVISIBLE);
                    }
                });
                //

            }
        });

        // gestion clique pour modification de courriel

        ivModifCourriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCourriel.setVisibility(View.INVISIBLE);
                Courriel.setVisibility(View.INVISIBLE);
                ivModifCourriel.setVisibility(View.INVISIBLE);

                // rend visible ce qui est cache
                etCourriel.setVisibility(View.VISIBLE);
                ivSaveCourriel.setVisibility(View.VISIBLE);
                ivSupCourriel.setVisibility(View.VISIBLE);

                UserV2  user = SharedPrefManager.getInstance(ProfileActivity.this).getUserV2();
                etCourriel.setText(user.getEmail());


                ivSaveCourriel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        courriel = etCourriel.getText().toString().trim();
                        boolean valide = true;
                        if (courriel.isEmpty()) {
                            etCourriel.setError(getString(R.string.erreur_courriel));
                            valide = false;
                        }
                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(courriel).matches()) {
                            etCourriel.setError(getString(R.string.erreur_courriel_valide));
                            valide = false;
                        }

                        if (valide) {
                            InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
                            // Users  request = new Users( name, courriel,password);
                            Log.d("token", "token" + token);
                            Log.d("locale", "locale" + locale);
                            Call<APIResponse> call = serveur.updateEmail(locale, "Bearer " + token,courriel);


                            call.enqueue(new Callback<APIResponse>() {
                                @Override
                                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                                    APIResponse resultat = response.body();
                                    Log.d("updateEmail", resultat.getMessage());
                                    if (resultat.getStatus().equals("success")) {
                                        SharedPrefManager.getInstance(ProfileActivity.this).updateEmail(courriel);
                                        UserV2  user = SharedPrefManager.getInstance(ProfileActivity.this).getUserV2();
                                        //  user.getName();
                                        tvCourriel.setText(user.getEmail());
                                        tvCourriel.setVisibility(View.VISIBLE);
                                        Courriel.setVisibility(View.VISIBLE);
                                        ivModifCourriel.setVisibility(View.VISIBLE);

                                        // rend visible ce qui est cache
                                        etCourriel.setVisibility(View.INVISIBLE);
                                        ivSaveCourriel.setVisibility(View.INVISIBLE);
                                        ivSupCourriel.setVisibility(View.INVISIBLE);

                                    } else {
                                        alertFail(resultat.getMessage());
                                    }
                                }

                                @Override
                                public void onFailure(Call<APIResponse> call, Throwable t) {
                                    Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    }
                });

                //
                ivSupCourriel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvCourriel.setVisibility(View.VISIBLE);
                        Courriel.setVisibility(View.VISIBLE);
                        ivSaveCourriel.setVisibility(View.VISIBLE);
                        ivModifCourriel.setVisibility(View.VISIBLE);
                        // rend visible ce qui est cache
                        etCourriel.setVisibility(View.INVISIBLE);
                        ivSaveCourriel.setVisibility(View.INVISIBLE);
                        ivSupCourriel.setVisibility(View.INVISIBLE);


                    }
                });
            }
        });

        // gerstion clique modification du mot de passe
        ivModifMotPasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvPassword.setVisibility(View.INVISIBLE);
                // Courriel.setVisibility(View.INVISIBLE);
                ivModifMotPasse.setVisibility(View.INVISIBLE);

                // rend visible ce qui est cache
                etAncienMotPasse.setVisibility(View.VISIBLE);
                etNouveauMotPasse.setVisibility(View.VISIBLE);
                etConfirmMotPasse.setVisibility(View.VISIBLE);
                ivAnnulerPassword.setVisibility(View.VISIBLE);
                ivSavePassword.setVisibility(View.VISIBLE);

                ivSavePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ancienMotPasse = etAncienMotPasse.getText().toString().trim();
                        motdepasse = etNouveauMotPasse.getText().toString().trim();
                        confirmMotPasse = etConfirmMotPasse.getText().toString().trim();
                        boolean valide = true;
                        if (ancienMotPasse.isEmpty()) {
                            etAncienMotPasse.setError(getString(R.string.ancien_mot_passe));
                            valide = false;
                        }
                        if (motdepasse.isEmpty()) {
                            etNouveauMotPasse.setError(getString(R.string.nouveau_mot_passe));
                            valide = false;
                        }
                        if (motdepasse.length() < 8) {
                            etNouveauMotPasse.setError(getString(R.string.caracteres_mot_passe));
                            valide = false;
                        }
                        if (confirmMotPasse.isEmpty()) {
                            etConfirmMotPasse.setError(getString(R.string.confirmez_le_mot_de_passe));
                            valide = false;
                        }
                        if (confirmMotPasse.length() < 8) {
                            etNouveauMotPasse.setError(getString(R.string.caracteres_mot_passe));
                            valide = false;
                        }
                        if (!confirmMotPasse.equals(motdepasse)) {
                            valide = false;
                            etNouveauMotPasse.setError(getString(R.string.mot_passe_identique));
                            etConfirmMotPasse.setError(getString(R.string.mot_passe_identique));
                        }
                        if (valide) {
                            // si valide ici
                            InterfaceAPI_V2 serveur = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
                            Log.d("token", "token" + token);
                            Log.d("locale", "locale" + locale);

                            Call<APIResponse> call = serveur.updatePassword(locale,"Bearer " + token,ancienMotPasse,motdepasse,confirmMotPasse);

                            call.enqueue(new Callback<APIResponse>() {
                                @Override
                                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                                    APIResponse resultat = response.body();
                                    Log.d("updatePassword", resultat.getMessage());
                                    if (resultat.getStatus().equals("success")) {
                                        SharedPrefManager.getInstance(ProfileActivity.this).updateEmail(courriel);
                                        UserV2  user = SharedPrefManager.getInstance(ProfileActivity.this).getUserV2();
                                        //  user.getName();
                                        tvPassword.setVisibility(View.VISIBLE);
                                        // Courriel.setVisibility(View.INVISIBLE);
                                        ivModifMotPasse.setVisibility(View.VISIBLE);

                                        // rend visible ce qui est cache
                                        etAncienMotPasse.setVisibility(View.INVISIBLE);
                                        etNouveauMotPasse.setVisibility(View.INVISIBLE);
                                        etConfirmMotPasse.setVisibility(View.INVISIBLE);
                                        ivAnnulerPassword.setVisibility(View.INVISIBLE);
                                        ivSavePassword.setVisibility(View.INVISIBLE);

                                    } else {
                                        alertFail(resultat.getMessage());
                                    }
                                }

                                @Override
                                public void onFailure(Call<APIResponse> call, Throwable t) {
                                    Toast.makeText(ProfileActivity.this, "une erreur", Toast.LENGTH_LONG).show();

                                }
                            });

                        }
                    }
                });

                ivAnnulerPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvPassword.setText("hello");
                        tvPassword.setVisibility(View.VISIBLE);
                        // Courriel.setVisibility(View.INVISIBLE);
                        ivModifMotPasse.setVisibility(View.VISIBLE);

                        // rend visible ce qui est cache
                        etAncienMotPasse.setVisibility(View.INVISIBLE);
                        etNouveauMotPasse.setVisibility(View.INVISIBLE);
                        etConfirmMotPasse.setVisibility(View.INVISIBLE);
                        ivAnnulerPassword.setVisibility(View.INVISIBLE);
                        ivSavePassword.setVisibility(View.INVISIBLE);

                    }
                });


            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des prescriptions");
        menu.findItem(R.id.itProfil).setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionPrescriptionActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
            startActivity(intent);
            return true;
        }  else if (item.getItemId() == R.id.itDeconnexion) {
            SharedPrefManager.getInstance(this).clear();
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadIDRefs() {
        nom = findViewById(R.id.nom);
        Courriel = findViewById(R.id.courriel);

        ivLogout = findViewById(R.id.ivLogout);
        ivEnregistrerNom = findViewById(R.id.ivEnregistrer);
        ivSupNom = findViewById(R.id.ivAnnuller);

        ivSaveCourriel = findViewById(R.id.ivEnregistrerCou);
        ivSupCourriel = findViewById(R.id.ivAnnulerCou);
        ivSavePassword = findViewById(R.id.ivSavePassword);
        ivAnnulerPassword = findViewById(R.id.ivAnnulerMotPasse);

        ivModifNom = findViewById(R.id.ivModifierNom);
        ivModifCourriel = findViewById(R.id.ivModifierCourriel);
        ivModifMotPasse = findViewById(R.id.ivModMoPasse);

        tvNomComplet = findViewById(R.id.tvNomComplet);
        tvCourriel = findViewById(R.id.tvCourriel);
        tvPassword = findViewById(R.id.tvMotpasse);

        etNom = findViewById(R.id.etNomComplet);
        etCourriel = findViewById(R.id.etCourrielmod);
        etMotdepasse = findViewById(R.id.etMotPasse);

        etAncienMotPasse = findViewById(R.id.etAncienMotPasse);
        etNouveauMotPasse = findViewById(R.id.etMotPasse);
        etConfirmMotPasse = findViewById(R.id.etConfirmMotdePasse);
    }

    private void LoadUserProfil() {
        user = SharedPrefManager.getInstance(this).getUserV2();
        token = SharedPrefManager.getInstance(this).getToken();
        tvNomComplet.setText(user.getName());
        tvCourriel.setText(user.getEmail());
        tvPassword.setText(getString(R.string.mot_de_passe));

    }

    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Users user = SharedPrefManager.getInstance(this).getUser();
            //   tvWelcome.setText("bienvenu "+ user.getName());
        /*
            Intent intent = new Intent(this,LoginActivitytest.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

*/
            //if()
        }

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

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                Glide.with(this).load(bitmap).into(imageViewProfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.logout(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse logoutResponse = response.body();
                Log.d("logout", logoutResponse.getMessage());
                if (logoutResponse.getStatus().equals("success")) {

                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("logout", t.getMessage());
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void update(String Nom, String courriel, String motdepasse) {
        InterfaceServeur serveur = RetrofitInstance.getInstance().create(InterfaceServeur.class);

        //Users  request = new Users( name, courriel,password);

        Call<ResponseBody> call = serveur.update(Nom, courriel, motdepasse);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody resultat = response.body();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "une erreur", Toast.LENGTH_LONG).show();


            }

        });
    }

    private void alertFail(String s) {
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

    private void SetLocale(String locale) {
        this.locale = locale;
    }
}