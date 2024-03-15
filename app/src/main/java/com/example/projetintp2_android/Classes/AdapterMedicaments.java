package com.example.projetintp2_android.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterMedicaments extends RecyclerView.Adapter<AdapterMedicaments.MonViewHolder> {
    private List<Prescriptions> liste;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public AdapterMedicaments(List<Prescriptions> liste) {
        this.liste = liste;
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

            icSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    supprimerMedicament(getAdapterPosition());
                }
            });
        }
    }
}
