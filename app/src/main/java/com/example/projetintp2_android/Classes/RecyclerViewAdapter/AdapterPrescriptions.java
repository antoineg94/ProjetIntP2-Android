package com.example.projetintp2_android.Classes.RecyclerViewAdapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.Classes.Objects.Medications;
import com.example.projetintp2_android.R;

import java.util.List;

public class AdapterPrescriptions extends RecyclerView.Adapter<AdapterPrescriptions.MyViewHolder> {

    private List<Medications> liste;
    private SparseBooleanArray checkedItems = new SparseBooleanArray();

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
        holder.checkBox.setChecked(checkedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomM;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomM = itemView.findViewById(R.id.tvNomM);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    final int adapterPosition = getAdapterPosition();
                    if (isChecked) {
                        // Décocher les autres cases
                        for (int i = 0; i < checkedItems.size(); i++) {
                            if (checkedItems.keyAt(i) != adapterPosition) {
                                checkedItems.put(checkedItems.keyAt(i), false);
                                final int finalI = i;
                                checkBox.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyItemChanged(checkedItems.keyAt(finalI));
                                    }
                                });
                            }
                        }
                        checkedItems.put(adapterPosition, true);
                    } else {
                        checkedItems.put(adapterPosition, false);
                    }
                    checkBox.post(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }
}
