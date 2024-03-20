package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.AdapterDispositifs;
import com.example.projetintp2_android.Classes.AdapterMedicaments;
import com.example.projetintp2_android.Classes.Dispositifs;
import com.example.projetintp2_android.Classes.Medicaments;

import java.util.ArrayList;
import java.util.List;

public class GestionDispositifs extends AppCompatActivity {

    RecyclerView rvDispositifs;
    AdapterDispositifs adapter;
    List<Dispositifs> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositifs);

        liste.add(new Dispositifs(1,"123123", "JS", 1));
        liste.add(new Dispositifs(2,"456456", "BS", 2));
        liste.add(new Dispositifs(3,"789789", "JD", 3));

        rvDispositifs= findViewById(R.id.rvListeDispositifs);
        rvDispositifs.setHasFixedSize(true);
        rvDispositifs.setLayoutManager(new LinearLayoutManager(this));

        AdapterDispositifs adapterDispositifs= new AdapterDispositifs(liste);
        rvDispositifs.setAdapter(adapterDispositifs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des dispositifs");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionMedicament.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDispositifs.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}