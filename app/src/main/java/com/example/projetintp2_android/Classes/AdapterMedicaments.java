package com.example.projetintp2_android.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetintp2_android.GestionMedicament;
import com.example.projetintp2_android.R;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterMedicaments extends RecyclerView.Adapter<AdapterMedicaments.MonViewHolder> {

    public interface InterfacePrescription
    {
        public void gestionClick(int position, Prescriptions prescriptions);
    }

    InterfacePrescription interfacePrescription;
    private GestionMedicament gestionMedicament;
    private List<Prescriptions> liste;

    public AdapterMedicaments(List<Prescriptions> liste, InterfacePrescription interfacePrescription) {
        this.liste = liste;
        this.interfacePrescription = interfacePrescription;
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_card, parent, false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
        Prescriptions prescription = liste.get(position);
        holder.tvNom.setText(prescription.getNameOfPrescription());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void supprimerMedicament(int position) {
        liste.remove(position);
        notifyItemRemoved(position);
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
                    supprimerMedicament(getAdapterPosition());
                }
            });
        }
    }
}
