package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ZoomPrescriptionActivity extends AppCompatActivity {

    TextView tvNomP, tvDateP, tvDateD, tvDateF, tvDoseP, tvFrequenceHP, tvFrequenceJours, tvFrequenceJP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_prescription);

        tvNomP = findViewById(R.id.tvNomP);
        tvDateP = findViewById(R.id.tvDateP);
        tvDateD = findViewById(R.id.tvDateDP);
        tvDateF = findViewById(R.id.tvDateFP);
        tvDoseP = findViewById(R.id.tvDoseP);
        tvFrequenceHP = findViewById(R.id.tvFrequenceHP);
        tvFrequenceJours = findViewById(R.id.tvFrequenceJours);
        tvFrequenceJP = findViewById(R.id.tvFrequenceJP);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        String dateP = intent.getStringExtra("dateP");
        String dateD = intent.getStringExtra("dateD");
        int dateF = intent.getIntExtra("dateF", 0);
        String dose = intent.getStringExtra("dose");
        int fHeures = intent.getIntExtra("fHeures", 0);
        int fJours = intent.getIntExtra("fJours", 0);
        int fParJours = intent.getIntExtra("fParJours", 0);

        tvNomP.setText(nom);
        tvDateP.setText(dateP);
        tvDateD.setText(dateD);
        tvDateF.setText(String.valueOf(dateF));
        tvDoseP.setText(dose);
        tvFrequenceHP.setText(String.valueOf(fHeures));
        tvFrequenceJours.setText(String.valueOf(fJours));
        tvFrequenceJP.setText(String.valueOf(fParJours));
        Log.e("TAG", tvFrequenceJours.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle(tvNomP.getText().toString());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Toast.makeText(this, "Vous êtes déjà dans la gestion des médicaments", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//Super-Gestion-Orange3
