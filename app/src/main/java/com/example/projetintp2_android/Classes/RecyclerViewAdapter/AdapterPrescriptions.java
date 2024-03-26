package com.example.projetintp2_android.Classes.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.R;

import java.util.List;

public class AdapterPrescriptions extends RecyclerView.Adapter<AdapterPrescriptions.MyViewHolder> {

    private List<Medications> liste;

    public AdapterPrescriptions(List<Medications> liste) {
        this.liste = liste;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_rv_medicament, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Medications medications = liste.get(position);
        holder.tvNomM.setText(medications.getName());
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomM;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomM = itemView.findViewById(R.id.tvNomM);
        }
    }
}
