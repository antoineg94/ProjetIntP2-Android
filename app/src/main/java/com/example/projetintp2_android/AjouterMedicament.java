package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AjouterMedicament extends AppCompatActivity {

    EditText edDateDebut, edDateFin;
    CheckBox chQutodien, chHebdomadaire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_medicament);

        edDateDebut = findViewById(R.id.edDateDebut);
        edDateFin = findViewById(R.id.edDateFin);
        chQutodien = findViewById(R.id.chQuotidien);
        chHebdomadaire = findViewById(R.id.chHebdomadaire);

        // Code pour vérifier et définir l'état de la CheckBox chQuotidien
        boolean isChecked = chQutodien.isChecked();
        chQutodien.setChecked(true); // pour cocher la CheckBox
        chQutodien.setChecked(false); // pour décocher la CheckBox

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
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked) {
                    // CheckBox cochée, faire quelque chose
                    Toast.makeText(AjouterMedicament.this, "CheckBox cochée", Toast.LENGTH_SHORT).show();
                }
                else{
                    // CheckBox décochée, faire quelque chose
                    Toast.makeText(AjouterMedicament.this, "CheckBox décochée", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chHebdomadaire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Récupérer les CheckBox enfants par leur ID
                CheckBox childCheckBox1 = findViewById(R.id.child_checkbox1);
                CheckBox childCheckBox2 = findViewById(R.id.child_checkbox2);
                // Ajouter ici les autres CheckBox enfants de la même manière

                // Cocher ou décocher les CheckBox enfants en fonction de l'état du CheckBox parent
                childCheckBox1.setChecked(isChecked);
                childCheckBox2.setChecked(isChecked);
                // Ajouter ici les autres CheckBox enfants de la même manière
            }
        });

        // Récupérer les CheckBox enfants par leur ID
        CheckBox childCheckBox1 = findViewById(R.id.child_checkbox1);
        CheckBox childCheckBox2 = findViewById(R.id.child_checkbox2);
        // Ajouter ici les autres CheckBox enfants de la même manière

        // Ajouter un écouteur de changement d'état aux CheckBox enfants
        CompoundButton.OnCheckedChangeListener childCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Traiter l'état des CheckBox enfants ici
                if (isChecked) {
                    Toast.makeText(AjouterMedicament.this, buttonView.getText() + " cochée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AjouterMedicament.this, buttonView.getText() + " décochée", Toast.LENGTH_SHORT).show();
                }
            }
        };

        childCheckBox1.setOnCheckedChangeListener(childCheckedChangeListener);
        childCheckBox2.setOnCheckedChangeListener(childCheckedChangeListener);
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
