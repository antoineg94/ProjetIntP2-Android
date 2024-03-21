package com.example.projetintp2_android.Classes.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.Classes.Objects.Dispositifs;
import com.example.projetintp2_android.R;

import java.util.List;

public class AdapterDispositifs extends RecyclerView.Adapter{

    List<Dispositifs> liste;

    public AdapterDispositifs(List<Dispositifs> liste)
    {
        this.liste=liste ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_card_dispositif,parent,false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterDispositifs.MonViewHolder monViewHolder=(AdapterDispositifs.MonViewHolder) holder;
        monViewHolder.tvNoSerie.setText(liste.get(position).getNoSerie());
        monViewHolder.tvNom.setText(liste.get(position).getAssociatedPatientFullName());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void supprimerDispositif(int position)
    {
        liste.remove(position);
        notifyItemRemoved(position);
    }

    public class MonViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNoSerie, tvNom;
        ImageView icSupprimer;
        public MonViewHolder(@NonNull View itemView){
            super(itemView);
            tvNoSerie= itemView.findViewById((R.id.tvNoSerie));
            tvNom= itemView.findViewById((R.id.tvNom));
            icSupprimer= itemView.findViewById((R.id.icSupprimer));

            icSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    supprimerDispositif(getLayoutPosition());
                }
            });
        }
    }
}
