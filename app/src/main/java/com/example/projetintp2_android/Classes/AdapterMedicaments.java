package com.example.projetintp2_android.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.R;

import java.util.List;

public class AdapterMedicaments extends RecyclerView.Adapter{
    List<Prescriptions> liste;
    public AdapterMedicaments(List<Prescriptions> liste)
    {
        this.liste=liste ;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_card,parent,false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MonViewHolder monViewHolder=(MonViewHolder) holder;
        monViewHolder.tvNom.setText(liste.get(position).getNameOfPrescription());
        monViewHolder.tvFonction.setText(liste.get(position).getFrequencyBetweenDosesInHours());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void supprimerMedicament(int position)
    {
        liste.remove(position);
        notifyItemRemoved(position);
    }

    public  class MonViewHolder extends  RecyclerView.ViewHolder
    {
        TextView tvNom, tvFonction;
        ImageView icSupprimer;
        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNom= itemView.findViewById((R.id.tvNom));
            tvFonction= itemView.findViewById((R.id.tvFonction));
            icSupprimer= itemView.findViewById((R.id.icSupprimer));

            icSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    supprimerMedicament(getLayoutPosition());
                }
            });

        }
    }
}
