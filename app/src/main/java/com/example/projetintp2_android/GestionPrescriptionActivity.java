package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Prescription;
import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterMedications;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.RetrofitInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionPrescriptionActivity extends AppCompatActivity implements AdapterMedications.InterfacePrescription {

    MainDB mainDB;
    PrescriptionDAO pdao;
    RecyclerView rvPrescriptions;
    AdapterMedications adapter;
    FloatingActionButton btAdd;
    List<Prescription> listePrescriptions;
    UserV2 user;
    String token, locale;


    // Formatter for dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);

        createLocalDB();

        LoadUserProfil();

        locale = "fr";
        btAdd = findViewById(R.id.btAdd);
        rvPrescriptions = findViewById(R.id.rvListeMedicaments);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionPrescriptionActivity.this, AddMedicamentActivity.class);
                startActivity(intent);
            }
        });

        getPrescriptions();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des prescriptions");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Toast.makeText(this, "Vous êtes déjà dans la gestion des médicaments", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDispositifsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itProfil) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.itDeconnexion) {
            SharedPrefManager.getInstance(this).clear();
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createLocalDB() {
        mainDB = Room.databaseBuilder(this, MainDB.class, "PrescriptionsDB")
                .allowMainThreadQueries()
                .build();
        pdao = mainDB.pdao();
    }

    private void getPrescriptions() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getPrescriptions(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getPrescriptionsList() != null) {
                    listePrescriptions = response.body().getData().getPrescriptionsList();
                    LoadPrescriptionsToLocalDB(listePrescriptions);
                    getApdaterPrescription();
                    Log.d("Prescriptions", listePrescriptions.toString());

                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
            }
        });
    }


    @Override
    public void gestionClick(int position, Prescription prescriptions) {
        Intent intent = new Intent(this, ZoomPrescriptionActivity.class);
        intent.putExtra("nom", prescriptions.getNameOfPrescription());
        intent.putExtra("dateP", prescriptions.getDateOfPrescription());
        intent.putExtra("dateD", prescriptions.getDateOfStart());
        intent.putExtra("dateF", prescriptions.getDurationOfPrescriptionInDays());
        intent.putExtra("dose", prescriptions.getFirstIntakeHour());
        intent.putExtra("fHeures", prescriptions.getFrequencyBetweenDosesInHours());
        intent.putExtra("fJours", prescriptions.getFrequencyOfIntakeInDays());
        intent.putExtra("fParJours", prescriptions.getFrequencyPerDay());
        startActivity(intent);
    }

    private void LoadUserProfil() {
        token = SharedPrefManager.getInstance(this).getToken();
    }

    private void LoadPrescriptionsToLocalDB(List<Prescription> list) {
        try {
            pdao.insertAllPrescriptions(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setAdapterPrescription(AdapterMedications adapter) {
        rvPrescriptions.setAdapter(adapter);
        rvPrescriptions.setHasFixedSize(true);
        rvPrescriptions.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getApdaterPrescription() {
        List<Prescription> list = pdao.getAllPrescriptions();
        adapter = new AdapterMedications(list, this);
        setAdapterPrescription(adapter);
    }
    private void logout() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.logout(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    SharedPrefManager.getInstance(GestionPrescriptionActivity.this).clear();
                    Intent intent = new Intent(GestionPrescriptionActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
            }
        });
    }
}
