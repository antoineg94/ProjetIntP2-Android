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

import com.example.projetintp2_android.GestionDispositifs;
import com.example.projetintp2_android.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ZoomPrescription extends AppCompatActivity {

    TextView tvNomP, tvDateP, tvDateDP, tvDateFP, tvDoseP, tvFrequenceHP, tvFrequenceJours, tvFrequenceJP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_prescription);

        tvNomP = findViewById(R.id.tvNomP);
        tvDateP = findViewById(R.id.tvDateP);
        tvDateDP = findViewById(R.id.tvDateDP);
        tvDateFP = findViewById(R.id.tvDateFP);
        tvDoseP = findViewById(R.id.tvDoseP);
        tvFrequenceHP = findViewById(R.id.tvFrequenceHP);
        tvFrequenceJours = findViewById(R.id.tvFrequenceJours);
        tvFrequenceJP = findViewById(R.id.tvFrequenceJP);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");

        long timestamp1 = intent.getLongExtra("dateP", 0);
        Calendar dateP = Calendar.getInstance();
        dateP.setTimeInMillis(timestamp1);

        long timestamp2 = intent.getLongExtra("dateD", 0);
        Date dateDP = new Date(timestamp2);

        int dateFP = intent.getIntExtra("dateF", 0);

        long timestamp4 = intent.getLongExtra("dose", 0);
        if (timestamp4 != 0) {
            Time dose = new Time(timestamp4);
            tvDoseP.setText(dose.toString());
        } else {
            tvDoseP.setText("Valeur de dose indisponible");
        }

        int fHeures = intent.getIntExtra("fHeures", 0);
        int fJours = intent.getIntExtra("fJours", 0);
        int fParJours = intent.getIntExtra("fParJours", 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        tvNomP.setText(nom);
        tvDateP.setText(dateFormat.format(dateP.getTime()));
        tvDateDP.setText(dateFormat.format(dateDP));
        tvDateFP.setText(String.valueOf(dateFP));

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
            Intent intent = new Intent(this, GestionDispositifs.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
