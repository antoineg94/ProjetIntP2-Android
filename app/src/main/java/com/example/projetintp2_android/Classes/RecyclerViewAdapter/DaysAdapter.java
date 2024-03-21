package com.example.projetintp2_android.Classes.RecyclerViewAdapter;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetintp2_android.R;

import java.util.ArrayList;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {
    private List<String> daysOfWeek;
    private SparseBooleanArray selectedItems;

    public DaysAdapter(List<String> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String day = daysOfWeek.get(position);
        holder.checkbox.setText(day);
        holder.checkbox.setChecked(selectedItems.get(position, false));
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            selectedItems.put(position, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return daysOfWeek.size();
    }

    public List<String> getSelectedDays() {
        List<String> selectedDays = new ArrayList<>();
        for (int i = 0; i < daysOfWeek.size(); i++) {
            if (selectedItems.get(i)) {
                selectedDays.add(daysOfWeek.get(i));
            }
        }
        return selectedDays;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox_day);
        }
    }
}
