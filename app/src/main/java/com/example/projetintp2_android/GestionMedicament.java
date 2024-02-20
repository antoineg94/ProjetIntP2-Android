package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class GestionMedicament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);
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
            Toast.makeText(this,"A`faire",Toast.LENGTH_LONG).show();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }
}