package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.AdapterMedicaments;
import com.example.projetintp2_android.Classes.InterfaceServeur;
import com.example.projetintp2_android.Classes.Medicaments;
import com.example.projetintp2_android.Classes.Retrofit;

import java.util.List;

public class GestionMedicament extends AppCompatActivity {

    RecyclerView rvMedicaments;
    AdapterMedicaments adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);

        rvMedicaments= findViewById(R.id.rvListeMedicaments);
        rvMedicaments.setHasFixedSize(true);
        rvMedicaments.setLayoutManager(new LinearLayoutManager(this));

        getMedicaments();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        setTitle("Pill Box");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.itGestionMedic)
        {
            Intent intent = new Intent(this, GestionMedicament.class);
            startActivity(intent);
            return true ;
        }
        else if(item.getItemId()== R.id.itDispositif)
        {
            Toast.makeText(this,"A faire",Toast.LENGTH_LONG).show();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMedicaments()
    {

    }
}