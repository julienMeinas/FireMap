package com.platine.firemap.presentation.fireworkdisplay.addFirework.fireworker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Fireworker;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FireworkerViewHolder> {
    private List<Fireworker_item> fireworker_items;
    private AddActionInterface addActionInterface;

    public RecyclerViewAdapter(AddActionInterface addActionInterface) {
        this.addActionInterface = addActionInterface;
        this.fireworker_items = new ArrayList<>();
    }

    public void bindViewModels(List<Fireworker_item> fireworker_items) {
        this.fireworker_items.clear();
        this.fireworker_items.addAll(fireworker_items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FireworkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fireworker_item, parent, false);
        RecyclerViewAdapter.FireworkerViewHolder fireworkViewHolder = new RecyclerViewAdapter.FireworkerViewHolder(v, this.addActionInterface);
        return fireworkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FireworkerViewHolder holder, int position) {
        holder.bind(fireworker_items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.fireworker_items.size();
    }

    public static class FireworkerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private AddActionInterface addActionInterface;
        private View v;
        private Fireworker_item fireworker_item;

        public FireworkerViewHolder(View v, AddActionInterface addActionInterface) {
            super(v);
            this.v = v;
            name = v.findViewById((R.id.name));
            this.addActionInterface = addActionInterface;
        }

        public void bind(Fireworker_item fireworker_item) {
            this.fireworker_item = fireworker_item;

            //name
            name.setText(fireworker_item.getName());

            this.v.findViewById(R.id.name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fireworker fireworker = new Fireworker(fireworker_item.getId(), fireworker_item.getName(), fireworker_item.getNote());
                    addActionInterface.selectFireworker(fireworker);
                }
            });

        }


    }
}
