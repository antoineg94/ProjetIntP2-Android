package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.projetintp2_android.Classes.DaysAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AjouterMedicament extends AppCompatActivity {

    EditText edDateDebut, edDateFin;
    CheckBox chQutodien, chHebdomadaire;
    Context context;
    TextView tvJours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_medicament);

        edDateDebut = findViewById(R.id.edDateDebut);
        edDateFin = findViewById(R.id.edDateFin);
        chQutodien = findViewById(R.id.chQuotidien);
        chHebdomadaire = findViewById(R.id.chHebdomadaire);
        tvJours = findViewById(R.id.tvJours);
        context = this;

        boolean isChecked1 = chQutodien.isChecked();
        chQutodien.setChecked(true); // pour cocher la CheckBox
        chQutodien.setChecked(false); // pour décocher la CheckBox

        boolean isChecked2 = chHebdomadaire.isChecked();
        chHebdomadaire.setChecked(true); // pour cocher la CheckBox
        chHebdomadaire.setChecked(false); // pour décocher la CheckBox

        edDateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateDebut();
            }
        });

        edDateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFin();
            }
        });

        chQutodien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // CheckBox cochée, faire quelque chose
                    Toast.makeText(AjouterMedicament.this, "CheckBox cochée", Toast.LENGTH_SHORT).show();
                } else {
                    // CheckBox décochée, faire quelque chose
                    Toast.makeText(AjouterMedicament.this, "CheckBox décochée", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chHebdomadaire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false);
                    builder.setTitle("Ajouter un joueur");

                    // Créer la RecyclerView avec l'adaptateur DaysAdapter
                    RecyclerView recyclerView = new RecyclerView(AjouterMedicament.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AjouterMedicament.this));
                    List<String> daysOfWeek = new ArrayList<>();
                    daysOfWeek.add("Lundi");
                    daysOfWeek.add("Mardi");
                    daysOfWeek.add("Mercredi");
                    daysOfWeek.add("Jeudi");
                    daysOfWeek.add("Vendredi");
                    daysOfWeek.add("Samedi");
                    daysOfWeek.add("Dimanche");
                    DaysAdapter adapter = new DaysAdapter(daysOfWeek);
                    recyclerView.setAdapter(adapter);

                    builder.setView(recyclerView);

                    // Définir le bouton "OK"
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            List<String> selectedDays = adapter.getSelectedDays();
                            StringBuilder selectedDaysText = new StringBuilder();
                            for (String day : selectedDays) {
                                selectedDaysText.append(day).append(", ");
                            }
                            String daysText = selectedDaysText.toString();
                            // Supprimer la virgule et l'espace à la fin
                            if (daysText.length() > 0) {
                                daysText = daysText.substring(0, daysText.length() - 2);
                            }
                            tvJours.setText(daysText);
                        }
                    });

                    // Définir le bouton "Annuler"
                    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    // CheckBox décochée, faire quelque chose
                    Toast.makeText(AjouterMedicament.this, "CheckBox décochée", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void DateDebut() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Mettez à jour le champ de texte avec la date sélectionnée
                edDateDebut.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void DateFin() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Mettez à jour le champ de texte avec la date sélectionnée
                edDateFin.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        setTitle("Ajouter un médicament");
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
}
