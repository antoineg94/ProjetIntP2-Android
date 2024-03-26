package com.example.projetintp2_android.Classes.RecyclerViewAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.Classes.APIResponses.APIResponse;
import com.example.projetintp2_android.Classes.DAO.DeviceDAO;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Devices;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;
import com.example.projetintp2_android.R;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDevices extends RecyclerView.Adapter {

    List<Devices> liste;
    String token, locale = "fr";
    Context parentContext;
    MainDB mainDB;
    DeviceDAO ddao;

    public AdapterDevices(List<Devices> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_card_dispositif, parent, false);
        parentContext = parent.getContext();
        token = SharedPrefManager.getInstance(parent.getContext()).getToken();
        mainDB = MainDB.getDatabase(parent.getContext());
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterDevices.MonViewHolder monViewHolder = (AdapterDevices.MonViewHolder) holder;
        monViewHolder.tvNoSerie.setText(liste.get(position).getNoSerie());
        monViewHolder.tvNom.setText(liste.get(position).getAssociatedPatientFullName());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void supprimerDispositif(int position) {
        liste.remove(position);
        notifyItemRemoved(position);

    }

    public void deleteDevicesFromDB(int position) {
        Devices device = liste.get(position);

        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.deleteDevices(locale, "Bearer " + token, device.getId());

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData() == null) {
                    deleteDeviceFromLocalDB(device, position);

                } else {
                    Toast.makeText(parentContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(parentContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDeviceFromLocalDB(Devices device, int position) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ddao = mainDB.ddao();
                    ddao.deleteDevice(device);
                    supprimerDispositif(position);
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                }
            }
        });

    }



public class MonViewHolder extends RecyclerView.ViewHolder {
    TextView tvNoSerie, tvNom;
    ImageView icSupprimer;

    public MonViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNoSerie = itemView.findViewById((R.id.tvNoSerie));
        tvNom = itemView.findViewById((R.id.tvNom));
        icSupprimer = itemView.findViewById((R.id.icSupprimer));

        icSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDevicesFromDB(getLayoutPosition());
            }
        });
    }
}
}
