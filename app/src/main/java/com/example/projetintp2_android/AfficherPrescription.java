package com.example.projetintp2_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AfficherPrescription extends AppCompatActivity {

    TextView tvNomP, tvDateP, tvDateDebut, tvDateFin, tvDureeP, tvPremiereDose, tvFreHeures, tvFreJours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_prescription);

        tvNomP = findViewById(R.id.tvNomP);
        tvDateP = findViewById(R.id.tvDateP);
        tvDateDebut = findViewById(R.id.tvDateDebut);
        tvDateFin = findViewById(R.id.tvDateFin);
        tvDureeP = findViewById(R.id.tvDureeP);
        tvPremiereDose = findViewById(R.id.tvPremiereDose);
        tvFreHeures = findViewById(R.id.tvFreHeures);
        tvFreJours = findViewById(R.id.tvFreJours);
    }
}