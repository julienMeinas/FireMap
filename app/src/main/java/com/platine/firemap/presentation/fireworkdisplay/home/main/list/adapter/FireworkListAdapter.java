package com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.Parking;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.InfoFireworkActivity;

import java.util.ArrayList;
import java.util.List;

public class FireworkListAdapter extends RecyclerView.Adapter<FireworkListAdapter.FireworkViewHolder> {
    private List<FireworkViewModel> fireworkViewModelList;
    private static final String TAG = "FireworkListAdapter";
    private FireworkActionInterface fireworkActionInterface;

    public FireworkListAdapter(FireworkActionInterface fireworkActionInterface) {
        fireworkViewModelList = new ArrayList<>();
        this.fireworkActionInterface = fireworkActionInterface;
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
        FireworkViewHolder fireworkViewHolder = new FireworkViewHolder(v, this.fireworkActionInterface);
        return fireworkViewHolder;
    }


    @Override
    public void onBindViewHolder(FireworkViewHolder holder, final int position) {
        holder.bind(fireworkViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return fireworkViewModelList.size();
    }



    public static class FireworkViewHolder extends RecyclerView.ViewHolder {
        private TextView address;
        private TextView date;
        private ImageView price;
        private ImageView parking;
        private ImageView accessHandicap;
        private ImageView people;
        private FireworkActionInterface fireworkActionInterface;
        private View v;
        private FireworkViewModel fireworkViewModel;
        CardView parentLayout;

        public FireworkViewHolder(View v, FireworkActionInterface fireworkActionInterface) {
            super(v);
            this.v = v;
            address = v.findViewById((R.id.address));
            date = v.findViewById(R.id.date);
            parentLayout = v.findViewById(R.id.parent_layout);
            price = v.findViewById(R.id.price);
            parking = v.findViewById(R.id.parking);
            accessHandicap = v.findViewById(R.id.accessHandicap);
            people = v.findViewById(R.id.people);
            this.fireworkActionInterface = fireworkActionInterface;
        }

        void bind(FireworkViewModel fireworkViewModel) {
            this.fireworkViewModel = fireworkViewModel;

            //Address
            address.setText(fireworkViewModel.getAddress());

            //Date
            String stringDate = fireworkViewModel.getDate();
            date.setText(stringDate);

            // price
            price.setImageResource(fireworkViewModel.getPrice() == 0 ? R.drawable.drawable_price_free : R.drawable.drawable_price_no_free);

            // parking
            if(fireworkViewModel.getParkings().size() == 0) {
                parking.setImageResource(R.drawable.drawable_no_parking);
            }else {
                boolean freeParking = false;
                for(Parking p : fireworkViewModel.getParkings()) {
                    Log.d(TAG, String.valueOf(p.getPrice()));
                    if(p.getPrice() == 0){
                        parking.setImageResource(R.drawable.drawable_parking_free);
                        freeParking = true;
                    }
                }
                if(!freeParking) {
                    parking.setImageResource(R.drawable.drawable_parking_no_free);
                }
            }

            // access handicap
            accessHandicap.setImageResource(fireworkViewModel.isHandicapAccess() ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);


            // people
            if(fireworkViewModel.getCrowded().equals("Low")) {
                people.setImageResource(R.drawable.drawable_people_low);
            }
            else if(fireworkViewModel.getCrowded().equals("Medium")) {
                people.setImageResource(R.drawable.drawable_people_medium);
            }
            else {
                people.setImageResource(R.drawable.drawable_people_high);
            }

            this.v.findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireworkActionInterface.onInfoClicked(fireworkViewModel.getAddress(), fireworkViewModel.getDate(),
                                                          fireworkViewModel.getPrice(), fireworkViewModel.isHandicapAccess(),
                                                          fireworkViewModel.getCrowded(), fireworkViewModel.getParkings(),
                                                          fireworkViewModel.getFireworker());
                }
            });
        }


    }
}
