package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.DAO.AlertDAO;
import com.example.projetintp2_android.Classes.DAO.CalendarDAO;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.DAO.LogDAO;
import com.example.projetintp2_android.Classes.DAO.MedicationDAO;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Alerts;
import com.example.projetintp2_android.Classes.Objects.Calendars;
import com.example.projetintp2_android.Classes.Objects.Devices;
import com.example.projetintp2_android.Classes.Objects.Logs;
import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.Classes.Objects.Prescription;
import com.example.projetintp2_android.Classes.Objects.UserV2;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterPrescriptions;
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionPrescriptionActivity extends AppCompatActivity implements AdapterPrescriptions.InterfacePrescription {

    MainDB mainDB;
    PrescriptionDAO pdao;
    MedicationDAO mdao;
    CalendarDAO cdao;
    AlertDAO adao;
    DeviceDAO ddao;
    LogDAO ldao;
    RecyclerView rvPrescriptions;
    AdapterPrescriptions adapter;
    FloatingActionButton btAdd;
    List<Prescription> listePrescriptions;
    UserV2 user;
    String token, locale;

    // Formatter for dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_medicament);

        createLocalDB();
        LoadUserProfil();

        locale = "fr";
        btAdd = findViewById(R.id.btAdd);
        rvPrescriptions = findViewById(R.id.rvListeMedicaments);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionPrescriptionActivity.this, AddPrescriptionActivity.class);
                startActivity(intent);
            }
        });

        loadAllDataToLocalDB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle(R.string.gestion_des_prescriptions);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itProfil) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.itDeconnexion) {
            SharedPrefManager.getInstance(this).clear();
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createLocalDB() {
        mainDB = MainDB.getDatabase(this);
        pdao = mainDB.pdao();
        mdao = mainDB.mdao();
        cdao = mainDB.cdao();
        adao = mainDB.adao();
        ddao = mainDB.ddao();
        ldao = mainDB.ldao();

    }

    private void loadAllDataToLocalDB() {
        getPrescriptions();
        getMedications();
        getCalendars();
        getAlerts();
        getDevices();
        //getLogs();
    }

    private void getPrescriptions() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getPrescriptions(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getPrescriptions() != null) {
                    listePrescriptions = response.body().getData().getPrescriptions();
                    LoadPrescriptionsToLocalDB(listePrescriptions);
                    getApdaterPrescription();
                    Log.d("Prescriptions", listePrescriptions.toString());

                } else {
                    Log.d("Prescriptions", "User don't have prescriptions yet.");
                    onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des prescriptions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMedications() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getMedications(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getMedications() != null) {
                    LoadMedicationsToLocalDB(response.body().getData().getMedications());
                    Log.d("Medications", response.body().getData().getMedications().toString());
                }
                else {
                    Log.d("Medications", "User don't have medications yet.");
                    onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des médicaments", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCalendars() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getCalendars(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getCalendars() != null) {
                    LoadCalendarsToLocalDB(response.body().getData().getCalendars());
                    Log.d("Calendars", response.body().getData().getCalendars().toString());
                }
                else {
                    Log.d("Calendars", "User don't have calendars yet.");
                   onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des calendriers", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getAlerts() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getAlerts(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getAlerts() != null) {
                    LoadAlertsToLocalDB(response.body().getData().getAlerts());
                    Log.d("Alerts", response.body().getData().getAlerts().toString());
                }
                else {
                    Log.d("Alerts", "User don't have alerts yet.");
                    onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des alertes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDevices() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getDevices(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData().getDevices() != null) {
                    LoadDevicesToLocalDB(response.body().getData().getDevices());
                    Log.d("Devices", response.body().getData().getDevices().toString());
                }
                else {
                    Log.d("Devices", "User don't have devices yet.");
                    onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des dispositifs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getLogs() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.getLogs(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                if (response.body().getData().getLogs() != null) {
                    LoadLogsToLocalDB(response.body().getData().getLogs());
                    Log.d("Logs", response.body().getData().getLogs().toString());
                } else {
                    Log.e("Logs", "User don't have logs yet.");
                    onFailure(call, new Throwable(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, "Erreur de chargement des logs", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void gestionClick(int position, Prescription prescriptions) {
        Intent intent = new Intent(this, ZoomPrescriptionActivity.class);
        intent.putExtra("nom", prescriptions.getNameOfPrescription());
        intent.putExtra("dateP", prescriptions.getDateOfPrescription());
        intent.putExtra("dateD", prescriptions.getDateOfStart());
        intent.putExtra("dateF", prescriptions.getDurationOfPrescriptionInDays());
        intent.putExtra("dose", prescriptions.getFirstIntakeHour());
        intent.putExtra("fHeures", prescriptions.getFrequencyBetweenDosesInHours());
        intent.putExtra("fJours", prescriptions.getFrequencyOfIntakeInDays());
        intent.putExtra("fParJours", prescriptions.getFrequencyPerDay());
        startActivity(intent);
    }

    private void LoadUserProfil() {
        token = SharedPrefManager.getInstance(this).getToken();
    }
    private void LoadPrescriptionsToLocalDB(List<Prescription> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pdao.insertAllPrescriptions(list);
                    Log.d("Prescriptions", pdao.getAllPrescriptions().toString());
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                }
            }
        });

    }
    private void LoadMedicationsToLocalDB(List<Medications> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mdao.insertAllMedications(list);
                    Log.d("Medications", mdao.getAfficherM().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void LoadCalendarsToLocalDB(List<Calendars> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    cdao.insertAllCalendars(list);
                    Log.d("Calendars", cdao.GetAllCalendars().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void LoadAlertsToLocalDB(List<Alerts> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    adao.insertAllAlerts(list);
                    Log.d("Alerts", adao.getAllAlerts().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void LoadDevicesToLocalDB(List<Devices> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ddao.insertAllDevices(list);
                    Log.d("Devices", ddao.getAllDevices().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void LoadLogsToLocalDB(List<Logs> list) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ldao.insertAllLogs(list);
                    Log.d("Logs", ldao.getAllLogs().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadPrescriptionsFromLocalDBToAdapter() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    listePrescriptions = pdao.getAllPrescriptions();
                    Log.d("Prescriptions", listePrescriptions.toString());
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getApdaterPrescription();
                    }
                });
            }
        });
    }


    private void setAdapterPrescription(AdapterPrescriptions adapter) {
        rvPrescriptions.setAdapter(adapter);
        rvPrescriptions.setHasFixedSize(true);
        rvPrescriptions.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getApdaterPrescription() {


        adapter = new AdapterPrescriptions(listePrescriptions, this);
        setAdapterPrescription(adapter);
    }

    private void logout() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.logout(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    SharedPrefManager.getInstance(GestionPrescriptionActivity.this).clear();
                    Intent intent = new Intent(GestionPrescriptionActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Log.e("logout", response.body().getMessage());
                    Toast.makeText(GestionPrescriptionActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("logout", t.getMessage());
                Toast.makeText(GestionPrescriptionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
