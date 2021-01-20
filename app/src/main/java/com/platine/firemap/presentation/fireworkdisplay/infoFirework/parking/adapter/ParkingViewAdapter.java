package com.platine.firemap.presentation.fireworkdisplay.infoFirework.parking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.AddActionInterface;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.Fireworker_item;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewAdapter extends RecyclerView.Adapter<ParkingViewAdapter.ParkingViewHolder>{
    private List<ParkingViewItem> parkings;

    public ParkingViewAdapter() {
        this.parkings = new ArrayList<>();
    }

    public void bindViewModels(List<ParkingViewItem> parkingViewItems) {
        this.parkings.clear();
        this.parkings.addAll(parkingViewItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parking_item, parent, false);
        ParkingViewAdapter.ParkingViewHolder parkingViewHolder = new ParkingViewAdapter.ParkingViewHolder(v);
        return parkingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        holder.bind(parkings.get(position));
    }

    @Override
    public int getItemCount() {
        return this.parkings.size();
    }

    public static class ParkingViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private View v;
        private ParkingViewItem parkingViewItem;

        public ParkingViewHolder(View v) {
            super(v);
            this.v = v;
            name = v.findViewById((R.id.name));
            price = v.findViewById((R.id.price));
        }

        public void bind(ParkingViewItem parkingViewItem) {
            this.parkingViewItem = parkingViewItem;

            //name
            name.setText(parkingViewItem.getName());
            price.setText(String.valueOf(parkingViewItem.getPrice()) + "€");
        }


    }
}
