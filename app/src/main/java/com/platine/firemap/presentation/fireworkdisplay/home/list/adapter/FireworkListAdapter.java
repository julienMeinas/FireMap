package com.platine.firemap.presentation.fireworkdisplay.home.list.adapter;

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
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FireworkListAdapter extends RecyclerView.Adapter<FireworkListAdapter.FireworkViewHolder> {
    private List<FireworkViewItem> fireworkViewModelList;
    private static final String TAG = "FireworkListAdapter";
    private FireworkActionInterface fireworkActionInterface;

    public FireworkListAdapter(FireworkActionInterface fireworkActionInterface) {
        fireworkViewModelList = new ArrayList<>();
        this.fireworkActionInterface = fireworkActionInterface;
    }

    public void bindViewModels(List<FireworkViewItem> fireworkViewModel) {
        this.fireworkViewModelList.clear();
        this.fireworkViewModelList.addAll(fireworkViewModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FireworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firework_itemv2, parent, false);
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
        private TextView city;
        private TextView price;
        private ImageView[] rateStars = new ImageView[5];
        private TextView nbAvis;
        private FireworkActionInterface fireworkActionInterface;
        private View v;
        private FireworkViewItem fireworkViewModel;
        CardView parentLayout;

        public FireworkViewHolder(View v, FireworkActionInterface fireworkActionInterface) {
            super(v);
            this.v = v;
            city = v.findViewById(R.id.city);
            address = v.findViewById((R.id.address));
            date = v.findViewById(R.id.date);
            price = v.findViewById(R.id.price);
            rateStars[0] = this.v.findViewById(R.id.rate_star_one);
            rateStars[1] = this.v.findViewById(R.id.rate_star_two);
            rateStars[2] = this.v.findViewById(R.id.rate_star_three);
            rateStars[3] = this.v.findViewById(R.id.rate_star_four);
            rateStars[4] = this.v.findViewById(R.id.rate_star_five);
            nbAvis = this.v.findViewById(R.id.numberAvis);
            parentLayout = v.findViewById(R.id.parent_layout);
            this.fireworkActionInterface = fireworkActionInterface;
        }

        public void bind(FireworkViewItem fireworkViewModel) {
            this.fireworkViewModel = fireworkViewModel;

            city.setText(fireworkViewModel.getCity());

            //Address
            address.setText(fireworkViewModel.getAddress());

            //Date
            String stringDate = fireworkViewModel.getDate();
            date.setText(stringDate);

            //price
            if(fireworkViewModel.getPrice() == 0)
                price.setText("Gratuit");
            else
                price.setText(fireworkViewModel.getPrice() + " €");

            for(int i =0; i<5 ;i++){
                if(fireworkViewModel.getNote()<i+0.25){
                    rateStars[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
                }else if(fireworkViewModel.getNote()>i+0.75){
                    rateStars[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
                }else {
                    rateStars[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
                }
            }

            nbAvis.setText("(" + fireworkViewModel.getAvis().size() + ")");


            this.v.findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FireworkModel fireworkModel = new FireworkModel(fireworkViewModel.getId(), fireworkViewModel.getCity(),
                                                                    fireworkViewModel.getAddress(), fireworkViewModel.getDate(),
                                                                    fireworkViewModel.getDescription(), fireworkViewModel.getPrice(),
                                                                    fireworkViewModel.isHandicapAccess(), fireworkViewModel.getDuration(),
                                                                    fireworkViewModel.getCrowded(), fireworkViewModel.getIdFireworker(),
                                                                    fireworkViewModel.getParkings(), fireworkViewModel.getAvis(),
                                                                    fireworkViewModel.getNote());

                    fireworkActionInterface.onInfoClicked(fireworkModel);
                }
            });
        }


        public String mapDate(Date date) {
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                    DateFormat.SHORT,
                    DateFormat.SHORT);
            return shortDateFormat.format(date);
        }


    }
}
