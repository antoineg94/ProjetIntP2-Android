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
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.RecyclerViewAdapter.DaysAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddMedicamentActivity extends AppCompatActivity {

    private static final String PREF_LANGUAGE_KEY = "pref_language";
    EditText edDateDebut, edDateFin;
    CheckBox chQuotidien, chHebdomadaire;
    Context context;
    TextView tvJours;
    Button btAjoutM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_medicament);

        String savedLanguage = getSavedLanguage();
        setLocale(savedLanguage);

        edDateDebut = findViewById(R.id.edDateDebut);
        edDateFin = findViewById(R.id.edDateFin);
        chQuotidien = findViewById(R.id.chQuotidien);
        chHebdomadaire = findViewById(R.id.chHebdomadaire);
        tvJours = findViewById(R.id.tvJours);
        btAjoutM = findViewById(R.id.btAjoutM);
        context = this;

        chQuotidien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chHebdomadaire.setChecked(false);
                    Toast.makeText(AddMedicamentActivity.this, "CheckBox cochée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMedicamentActivity.this, "CheckBox décochée", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chHebdomadaire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chQuotidien.setChecked(false);
                    afficherFenetreDialogueSelectionJours();
                } else {
                    tvJours.setText("");
                }
            }
        });
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
        btAjoutM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void afficherFenetreDialogueSelectionJours() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("Sélectionner les jours");

        RecyclerView recyclerView = new RecyclerView(AddMedicamentActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddMedicamentActivity.this));
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

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<String> selectedDays = adapter.getSelectedDays();
                StringBuilder selectedDaysText = new StringBuilder();
                for (String day : selectedDays) {
                    selectedDaysText.append(day).append(", ");
                }
                String daysText = selectedDaysText.toString();
                if (daysText.length() > 0) {
                    daysText = daysText.substring(0, daysText.length() - 2);
                }
                tvJours.setText(daysText);
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void DateDebut() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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
                edDateFin.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Ajouter une prescription");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionMedicamentActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDispositifsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_language_switch) {
            Switch languageSwitch = findViewById(R.id.languageSwitch);
            boolean isChecked = languageSwitch.isChecked();

            if (isChecked) {
                saveLanguage("fr");
            } else {
                saveLanguage("en");
            }

            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveLanguage(String language) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_LANGUAGE_KEY, language);
        editor.apply();
    }
    private String getSavedLanguage() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString(PREF_LANGUAGE_KEY, "fr");
    }
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = getResources().getConfiguration();
        config.setLocale(locale);
        Context context = createConfigurationContext(config);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        /*Locale locale = new Locale(languageCode);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // Redémarrez votre activité pour appliquer les modifications
        recreate();*/
    }
}
