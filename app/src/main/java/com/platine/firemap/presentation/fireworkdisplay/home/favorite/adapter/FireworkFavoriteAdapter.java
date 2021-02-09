package com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter;

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

public class FireworkFavoriteAdapter extends RecyclerView.Adapter<FireworkFavoriteAdapter.FireworkViewHolder> {
    private final static String TAG = "FireworkFavoriteRecyclerViewAdapter";
    private List<FireworkViewItem> fireworks;
    private FireworkActionInterface fireworkActionInterface;

    public FireworkFavoriteAdapter(FireworkActionInterface fireworkActionInterface) {
        this.fireworkActionInterface = fireworkActionInterface;
        this.fireworks = new ArrayList<>();
    }

    public void bindViewModels(List<FireworkViewItem> fireworkViewModels) {
        this.fireworks.clear();
        this.fireworks.addAll(fireworkViewModels);
        notifyDataSetChanged();
    }

    public FireworkFavoriteAdapter.FireworkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);
        FireworkFavoriteAdapter.FireworkViewHolder articleViewHolder = new FireworkFavoriteAdapter.FireworkViewHolder(v, fireworkActionInterface);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FireworkFavoriteAdapter.FireworkViewHolder holder, int position) {
        holder.bind(fireworks.get(position));
    }

    public int getItemCount() {
        return fireworks.size();
    }


    public static class FireworkViewHolder extends RecyclerView.ViewHolder {
        private TextView city;
        private TextView address;
        private TextView date;
        private TextView price;
        private ImageView[] rateStars = new ImageView[5];
        private FireworkActionInterface fireworkActionInterface;
        private View v;
        private FireworkViewItem fireworkViewItem;
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
            parentLayout = v.findViewById(R.id.parent_layout);
            this.fireworkActionInterface = fireworkActionInterface;
        }

        public void bind(FireworkViewItem fireworkViewItem) {
            this.fireworkViewItem = fireworkViewItem;

            city.setText(fireworkViewItem.getCity());

            //Address
            address.setText(fireworkViewItem.getAddress());

            //Date
            String stringDate = fireworkViewItem.getDate();
            date.setText(stringDate);

            //price
            if(fireworkViewItem.getPrice() == 0)
                price.setText("Gratuit");
            else
                price.setText(fireworkViewItem.getPrice() + " €");

            for(int i =0; i<5 ;i++){
                if(fireworkViewItem.getNote()<i+0.25){
                    rateStars[i].setImageResource(R.drawable.rate_star_big_off_holo_dark);
                }else if(fireworkViewItem.getNote()>i+0.75){
                    rateStars[i].setImageResource(R.drawable.rate_star_big_on_holo_dark);
                }else {
                    rateStars[i].setImageResource(R.drawable.rate_star_big_half_holo_dark);
                }
            }



            this.v.findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FireworkModel fireworkModel = new FireworkModel(fireworkViewItem.getId(), fireworkViewItem.getCity(),
                            fireworkViewItem.getAddress(), fireworkViewItem.getDate(),
                            fireworkViewItem.getDuration(), fireworkViewItem.getPrice(),
                            fireworkViewItem.isHandicapAccess(), fireworkViewItem.getDuration(),
                            fireworkViewItem.getCrowded(), fireworkViewItem.getIdFireworker(),
                            fireworkViewItem.getParkings(), fireworkViewItem.getAvis(),
                            fireworkViewItem.getNote());

                    fireworkActionInterface.onInfoClicked(fireworkModel);
                }
            });

            this.v.findViewById(R.id.button_remove).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireworkActionInterface.removeFavorite(fireworkViewItem.getId());
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
