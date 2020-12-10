package com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;

import java.util.ArrayList;
import java.util.List;

public class FireworkListAdapter extends RecyclerView.Adapter<FireworkListAdapter.FireworkViewHolder> {
    private List<FireworkViewModel> fireworkViewModelList;

    public FireworkListAdapter() {
        fireworkViewModelList = new ArrayList<>();
    }

    public void bindViewModels(List<FireworkViewModel> fireworkViewModel) {
        this.fireworkViewModelList.clear();
        this.fireworkViewModelList.addAll(fireworkViewModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FireworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firework_item, parent, false);
        FireworkViewHolder fireworkViewHolder = new FireworkViewHolder(v);
        return fireworkViewHolder;
    }


    @Override
    public void onBindViewHolder(FireworkViewHolder holder, final int position) {
        holder.bind(fireworkViewModelList.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO show detail of the firework
            }
        });

    }

    @Override
    public int getItemCount() {
        return fireworkViewModelList.size();
    }



    public static class FireworkViewHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView date;
        private View v;
        private FireworkViewModel fireworkViewModel;
        CardView parentLayout;

        public FireworkViewHolder(View v) {
            super(v);
            this.v = v;
            address = v.findViewById((R.id.address));
            date = v.findViewById(R.id.date);
            parentLayout = v.findViewById(R.id.parent_layout);
        }

        void bind(FireworkViewModel fireworkViewModel) {
            this.fireworkViewModel = fireworkViewModel;

            //Address
            address.setText(fireworkViewModel.getAddress());

            //Date
            String stringDate = convertJsonToStringDate(fireworkViewModel.getDate());
            date.setText(stringDate);
        }

        private String convertJsonToStringDate(String date) {
            return date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4);
        }
    }
}
