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

import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterMedications;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceServeur;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Databases.PrescriptionDB;
import com.example.projetintp2_android.Classes.Objects.Prescriptions;
import com.example.projetintp2_android.Classes.Retrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionPrescriptionActivity extends AppCompatActivity implements AdapterMedications.InterfacePrescription{

    PrescriptionDB pdb;
    PrescriptionDAO pdao;
    RecyclerView rvMedicaments;
    AdapterMedications adapter;
    FloatingActionButton btAdd;
    List<Prescriptions> listePrescriptions;
    UserV2 user;
    String token,locale;


    // Formatter for dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);

        pdb = Room.databaseBuilder(this, PrescriptionDB.class, "PrescriptionsDB")
                .allowMainThreadQueries()
                .build();

        pdao = pdb.pdao();
        LoadUserProfil();
        locale = "fr";
        btAdd = findViewById(R.id.btAdd);

        rvMedicaments = findViewById(R.id.rvListeMedicaments);
        rvMedicaments.setHasFixedSize(true);
        rvMedicaments.setLayoutManager(new LinearLayoutManager(this));
        listePrescriptions = new ArrayList<>();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionPrescriptionActivity.this, AddMedicamentActivity.class);
                startActivity(intent);
            }
        });

        getMedicaments();
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMedicaments() {
        InterfaceAPI_V2 serveur = Retrofit.getInstance().create(InterfaceAPI_V2.class);
        Call<List<Prescriptions>> call = serveur.getPrescriptions(locale,token);

        call.enqueue(new Callback<List<Prescriptions>>() {
            @Override
            public void onResponse(Call<List<Prescriptions>> call, Response<List<Prescriptions>> response) {
                if (response.isSuccessful()) {
                    listePrescriptions = response.body();
                    adapter = new AdapterMedications(listePrescriptions, GestionPrescriptionActivity.this);
                    rvMedicaments.setAdapter(adapter);
                    pdao.insertAllPrescriptions(listePrescriptions);

                } else {
                    Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des médicaments", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Code d'erreur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Prescriptions>> call, Throwable t) {
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de connexion au serveur", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "Erreur de connexion au serveur : " + t.getMessage());
            }
        });
    }
    @Override
    public void gestionClick(int position, Prescriptions prescriptions) {
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

    private  void LoadUserProfil(){
        token = SharedPrefManager.getInstance(this).getToken();

    }
}
