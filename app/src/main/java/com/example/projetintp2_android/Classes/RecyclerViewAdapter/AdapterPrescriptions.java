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
import com.example.projetintp2_android.Classes.DAO.PrescriptionDAO;
import com.example.projetintp2_android.Classes.Databases.MainDB;
import com.example.projetintp2_android.Classes.Interfaces.InterfaceAPI_V2;
import com.example.projetintp2_android.Classes.Objects.Prescription;
import com.example.projetintp2_android.Classes.Retrofit.RetrofitInstance;
import com.example.projetintp2_android.Classes.SharedPrefs.SharedPrefManager;
import com.example.projetintp2_android.GestionPrescriptionActivity;
import com.example.projetintp2_android.R;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPrescriptions extends RecyclerView.Adapter<AdapterPrescriptions.MonViewHolder> {

    Context parentContext;
    String token, locale;
    MainDB mainDB;
    PrescriptionDAO pdao;
    InterfacePrescription interfacePrescription;
    private GestionPrescriptionActivity gestionMedicament;
    private final List<Prescription> liste;
    public AdapterPrescriptions(List<Prescription> liste, InterfacePrescription interfacePrescription) {
        this.liste = liste;
        this.interfacePrescription = interfacePrescription;
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_card, parent, false);
        parentContext = parent.getContext();
        locale = Locale.getDefault().getLanguage();
        token = SharedPrefManager.getInstance(parent.getContext()).getToken();
        mainDB = MainDB.getDatabase(parent.getContext());
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        Prescription prescription = liste.get(position);
        holder.tvNom.setText(prescription.getNameOfPrescription());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void supprimerPrescription(int position) {
        liste.remove(position);
        notifyItemRemoved(position);
    }

    private void deletePrescriptionsFromDB(int position) {
        Prescription prescription = liste.get(position);
        Log.d("Prescription", prescription.toString() + " " + position);
        Log.d("Token", token);
        Log.d("Locale", locale);
        InterfaceAPI_V2 api = RetrofitInstance.getInstance().create(InterfaceAPI_V2.class);
        Call<APIResponse> call = api.deletePrescriptions(locale, "Bearer " + token, prescription.getId());

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getData() == null) {
                    deleteDeviceFromLocalDB(prescription, position);
                    Toast.makeText(parentContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();

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

    private void deleteDeviceFromLocalDB(Prescription prescription, int position) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    pdao = mainDB.pdao();
                    pdao.deletePrescription(prescription);
                    supprimerPrescription(position);
                } catch (Exception e) {
                    Log.e("Erreur", e.getMessage());
                    Toast.makeText(parentContext, "Erreur lors de la suppression du dispositif", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public interface InterfacePrescription {
        void gestionClick(int position, Prescription prescriptions);
    }

    public class MonViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom;
        ImageView icSupprimer;

        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNom = itemView.findViewById(R.id.tvNom);
            icSupprimer = itemView.findViewById(R.id.icSupprimer);

            tvNom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfacePrescription.gestionClick(getLayoutPosition(), liste.get(getLayoutPosition()));
                }
            });
            icSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deletePrescriptionsFromDB(getAdapterPosition());
                }
            });
        }
    }
}
