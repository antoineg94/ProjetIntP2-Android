package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.AdapterMedicaments;
import com.example.projetintp2_android.Classes.InterfaceServeur;
import com.example.projetintp2_android.Classes.Prescriptions;
import com.example.projetintp2_android.Classes.Retrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionMedicament extends AppCompatActivity {

    RecyclerView rvMedicaments;
    AdapterMedicaments adapter;
    FloatingActionButton btAdd;
    List<Prescriptions> listePrescriptions;

    // Formatter for dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);

        btAdd = findViewById(R.id.btAdd);

        rvMedicaments = findViewById(R.id.rvListeMedicaments);
        rvMedicaments.setHasFixedSize(true);
        rvMedicaments.setLayoutManager(new LinearLayoutManager(this));
        listePrescriptions = new ArrayList<>();


        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionMedicament.this, AjouterMedicament.class);
                startActivity(intent);
            }
        });

        getMedicaments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des médicaments");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Toast.makeText(this, "Vous êtes déjà dans la gestion des médicaments", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDispositifs.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMedicaments() {
        InterfaceServeur serveur = Retrofit.getInstance().create(InterfaceServeur.class);
        Call<List<Prescriptions>> call = serveur.getMedicaments();
        call.enqueue(new Callback<List<Prescriptions>>() {
            @Override
            public void onResponse(Call<List<Prescriptions>> call, Response<List<Prescriptions>> response) {
                if (response.isSuccessful()) {
                    listePrescriptions = response.body();
                    adapter = new AdapterMedicaments(listePrescriptions);
                    rvMedicaments.setAdapter(adapter);
                } else {
                    Toast.makeText(GestionMedicament.this, "Erreur de chargement des médicaments", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Code d'erreur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Prescriptions>> call, Throwable t) {
                Toast.makeText(GestionMedicament.this, "Erreur de connexion au serveur", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "Erreur de connexion au serveur : " + t.getMessage());
            }
        });
    }
}
