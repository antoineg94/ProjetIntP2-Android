package com.example.projetintp2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.RecyclerViewAdapter.AdapterDevices;
import com.example.projetintp2_android.Classes.Objects.Devices;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;

import java.util.ArrayList;
import java.util.List;

public class GestionDevicesActivity extends AppCompatActivity {

    RecyclerView rvDispositifs;
    AdapterDevices adapter;
    List<Devices> list;
    MainDB mainDB;
    DeviceDAO ddao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_dispositifs);

        /*liste.add(new Devices(1,"123123", "JS", 1,null,null));
        liste.add(new Devices(2,"456456", "BS", 2,null,null));
        liste.add(new Devices(3,"789789", "JD", 3,null,null));*/
        createLocalDB();
        getDevicesFromLocalDB();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        setTitle("Gestion des dispositifs");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itGestionMedic) {
            Intent intent = new Intent(this, GestionPrescriptionActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.itDispositif) {
            Intent intent = new Intent(this, GestionDevicesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createLocalDB() {
        mainDB = Room.databaseBuilder(this, MainDB.class, "MainDB").allowMainThreadQueries().build();
        ddao = mainDB.ddao();

    }

    private void getDevicesFromLocalDB() {
        list = ddao.getAllDevices();

    }

    private void setAdapterDevices(AdapterDevices adapter) {
        rvDispositifs = findViewById(R.id.rvListeDispositifs);
        rvDispositifs.setAdapter(adapter);
        rvDispositifs.setHasFixedSize(true);
        rvDispositifs.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAdapterDevices() {
        adapter = new AdapterDevices(list);
        setAdapterDevices(adapter);

    }
}