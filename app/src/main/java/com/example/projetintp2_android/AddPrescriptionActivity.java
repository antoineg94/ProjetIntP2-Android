package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.DAO.AlertDAO;
import com.example.projetintp2_android.Classes.DAO.CalendarDAO;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.DAO.LogDAO;
import com.example.projetintp2_android.Classes.DAO.MedicationDAO;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.Classes.Objects.Prescription;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterPrescriptions;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrescriptionActivity extends AppCompatActivity {

    private static final String PREF_LANGUAGE_KEY = "pref_language";
    EditText edNameOfPrescription, edDurationOfPrescriptionInDays, edFrequencyOfIntakeInDays;
    DatePicker edDateOfPrescription, edDateOfStart;
    TimePicker edFrequencyBetweenDosesInHours, edFirstIntakeHour;
    Context context;
    Button btAjoutM;

    List<Medications> listeMedications;
    MainDB mainDB;
    PrescriptionDAO pdao;
    MedicationDAO mdao;
    CalendarDAO cdao;
    AlertDAO adao;
    DeviceDAO ddao;
    LogDAO ldao;
    AdapterPrescriptions adapter;
    Spinner spinner;
    RecyclerView rvM;
    String nameOfPrescription, locale, token;
    Date dateOfPrescription, dateOfStart;
    Time firstIntakeHour;
    int durationOfPrescriptionInDays,
            frequencyBetweenDosesInHours, frequencyOfIntakeInDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_prescription);

        String savedLanguage = getSavedLanguage();
        setLocale(savedLanguage);

        createLocalDB();
        LoadUserProfil();

        locale = "fr";
        LoadRefsForUI();

        btAjoutM = findViewById(R.id.btAjoutM);
        spinner = findViewById(R.id.spinner);
        rvM = findViewById(R.id.rvM);
        context = this;
        btAjoutM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void LoadRefsForUI() {
        edNameOfPrescription = findViewById(R.id.edNameOfPrescription);
        edDurationOfPrescriptionInDays = findViewById(R.id.edDurationOfPrescriptionInDays);
        edFrequencyBetweenDosesInHours = findViewById(R.id.edFrequencyBetweenDosesInHours);
        edFrequencyOfIntakeInDays = findViewById(R.id.edFrequencyOfIntakeInDays);
        edDateOfPrescription = findViewById(R.id.edDateOfPrescription);
        edDateOfStart = findViewById(R.id.edDateOfStart);
        edFirstIntakeHour = findViewById(R.id.edFirstIntakeHour);

    }

    private void createLocalDB() {
        mainDB = MainDB.getDatabase(this);
        mdao = mainDB.mdao();
        pdao = mainDB.pdao();
    }


    private void LoadUserProfil() {
        token = SharedPrefManager.getInstance(this).getToken();
    }

    private void LoadMedicationsToLocalDB(List<Medications> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    listeMedications = mdao.getAfficherM();
                    Log.d("Medications", mdao.getAfficherM().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Erreur lors de la récupération des médicaments", Toast.LENGTH_SHORT).show();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getAdapterMedicament();
                    }
                });
            }
        });
    }

    private void addPrescriptionToLocalDB(Prescription prescription) {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Medications medication = mdao.getMedicationById(spinner.getSelectedItemPosition());
                pdao.insertPrescription(prescription);
                Toast.makeText(context, "Prescription ajoutée", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Prescription ajoutée", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void addPrescriptionToDistantDB() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.postPrescriptions(locale, token, nameOfPrescription, dateOfPrescription.toString(), dateOfStart.toString(), firstIntakeHour.toString(), durationOfPrescriptionInDays, frequencyBetweenDosesInHours, frequencyOfIntakeInDays);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("error")) {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.body().getData() != null) {
                    Prescription prescription = response.body().getData().getPrescriptions().get(0);
                    addPrescriptionToLocalDB(prescription);
                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(context, "Erreur lors de l'ajout de la prescription", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapterMedicament(AdapterPrescriptions adapter) {
        rvM.setAdapter(adapter);
        rvM.setHasFixedSize(true);
        rvM.setLayoutManager(new LinearLayoutManager(this));

        /*spinner.setOnTouchListener((v, event) -> {
            // Afficher ou masquer le RecyclerView en fonction de l'état actuel
            if (rvM.getVisibility() == View.VISIBLE) {
                rvM.setVisibility(View.GONE);
                Log.d("Médicaments", "sdfdsfsdf donnée récupérée");
            } else {
                rvM.setVisibility(View.VISIBLE);
            }
            return false;
        });*/
    }

    private void getAdapterMedicament() {
        adapter = new AdapterPrescriptions(listeMedications);
        setAdapterMedicament(adapter);
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
            Intent intent = new Intent(this, GestionPrescriptionActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
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
