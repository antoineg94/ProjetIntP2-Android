package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterDevices;
import com.example.projetintp2_android.Classes.Objects.Devices;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionDevicesActivity extends AppCompatActivity {

    RecyclerView rvDispositifs;
    AdapterDevices adapter;
    List<Devices> list;
    MainDB mainDB;
    DeviceDAO ddao;
    String token, locale = "fr";

    EditText etNoSerie, etAsssociatedPatientFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositifs);
        rvDispositifs = findViewById(R.id.rvListeDispositifs);

        /*liste.add(new Devices(1,"123123", "JS", 1,null,null));
        liste.add(new Devices(2,"456456", "BS", 2,null,null));
        liste.add(new Devices(3,"789789", "JD", 3,null,null));*/
        LoadUserProfil();
        createLocalDB();
        getDevicesFromLocalDB();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des dispositifs");
        menu.getItem(1).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionPrescriptionActivity.class);
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
        ddao = mainDB.ddao();

    }

    private void getDevicesFromLocalDB() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    list = ddao.getAllDevices();
                    Log.d("Device list", list.toString());
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getAdapterDevices();
                    }
                });
            }
        });
    }

    private void setAdapterDevices(AdapterDevices adapter) {
        rvDispositifs.setAdapter(adapter);
        rvDispositifs.setHasFixedSize(true);
        rvDispositifs.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAdapterDevices() {
        adapter = new AdapterDevices(list);
        setAdapterDevices(adapter);
    }

    private void LoadUserProfil() {
        token = SharedPrefManager.getInstance(this).getToken();
    }

    private void addDeviceToLocalDB(Devices device) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ddao.insertDevice(device);
                    getDevicesFromLocalDB();
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                }
            }
        });
    }

    private void validateEditText() {
        if (etNoSerie.getText().toString().isEmpty()) {
            etNoSerie.setError("Veuillez entrer le numéro de série");
            return;
        }
        if (etAsssociatedPatientFullName.getText().toString().isEmpty()) {
            etAsssociatedPatientFullName.setError("Veuillez entrer le nom du patient associé");
            return;
        }
        createDevice();

    }

    private void createDevice() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        String noSerie = etNoSerie.getText().toString();
        String associatedPatientFullName = etAsssociatedPatientFullName.getText().toString();
        Call<APIResponse> call = api.postDevices(locale, "Bearer " + token, noSerie, associatedPatientFullName);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    List<Devices> list = response.body().getData().getDevices();
                    for (Devices device : list) {
                        addDeviceToLocalDB(device);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
            }
        });
    }

    private void logout() {
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.logout(locale, "Bearer " + token);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("success")) {
                    SharedPrefManager.getInstance(GestionDevicesActivity.this).clear();
                    Intent intent = new Intent(GestionDevicesActivity.this, LoginActivitytest.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e("Erreur", t.getMessage());
            }
        });
    }
}