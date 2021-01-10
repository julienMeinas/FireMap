package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.platine.firemap.R;

import java.util.ArrayList;
import java.util.List;

public class FireworkerAvisAdapter extends RecyclerView.Adapter<FireworkerAvisAdapter.FireworkerAvisViewHolder> {
    private List<FireworkerAvisViewItem> fireworkerAvisViewModelList;

    public FireworkerAvisAdapter() {
        fireworkerAvisViewModelList = new ArrayList<>();
    }

    public void bindViewModels(List<FireworkerAvisViewItem> fireworkerAvisViewModel) {
        this.fireworkerAvisViewModelList.clear();
        this.fireworkerAvisViewModelList.addAll(fireworkerAvisViewModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FireworkerAvisAdapter.FireworkerAvisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firework_item, parent, false);
        FireworkerAvisAdapter.FireworkerAvisViewHolder fireworkerAvisViewHolder = new FireworkerAvisAdapter.FireworkerAvisViewHolder(v);
        return fireworkerAvisViewHolder;
    }

    @Override
    public void onBindViewHolder(FireworkerAvisAdapter.FireworkerAvisViewHolder holder, final int position) {
        holder.bind(fireworkerAvisViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return fireworkerAvisViewModelList.size();
    }


    public static class FireworkerAvisViewHolder extends RecyclerView.ViewHolder {
        private View v;

        public FireworkerAvisViewHolder(View v) {
            super(v);
            this.v = v;
        }

        public void bind(FireworkerAvisViewItem fireworkerAvisViewModel) {
            //TODO
        }
    }
}
