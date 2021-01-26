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
        private ImageView price;
        private ImageView parking;
        private ImageView accessHandicap;
        private ImageView people;
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
            parentLayout = v.findViewById(R.id.parent_layout);
            price = v.findViewById(R.id.price);
            parking = v.findViewById(R.id.parking);
            accessHandicap = v.findViewById(R.id.accessHandicap);
            people = v.findViewById(R.id.people);
            this.fireworkActionInterface = fireworkActionInterface;
        }

        public void bind(FireworkViewItem fireworkViewItem) {
            this.fireworkViewItem = fireworkViewItem;

            city.setText(fireworkViewItem.getCity());

            //Address
            address.setText(fireworkViewItem.getAddress());

            //Date
            Date stringDate = fireworkViewItem.getDate();
            date.setText(mapDate(stringDate));


            /**
            // price
            price.setImageResource(fireworkViewItem.getPrice() == 0 ? R.drawable.drawable_price_free : R.drawable.drawable_price_no_free);

            // parking
            if(fireworkViewItem.getParkings().size() == 0) {
                parking.setImageResource(R.drawable.drawable_no_parking);
            }else {
                boolean freeParking = false;
                for(Parking p : fireworkViewItem.getParkings()) {
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
            accessHandicap.setImageResource(fireworkViewItem.isHandicapAccess() ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);

            // people
            if(fireworkViewItem.getCrowded().equals("Low")) {
                people.setImageResource(R.drawable.drawable_people_low);
            }
            else if(fireworkViewItem.getCrowded().equals("Medium")) {
                people.setImageResource(R.drawable.drawable_people_medium);
            }
            else {
                people.setImageResource(R.drawable.drawable_people_high);
            }
            */
            this.v.findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Fireworker> fireworkers = new ArrayList<>();
                    fireworkers.add(fireworkViewItem.getFireworker());
                    FireworkModel fireworkModel = new FireworkModel(fireworkViewItem.getId(), fireworkViewItem.getCity(),
                            fireworkViewItem.getAddress(),
                            fireworkViewItem.getDate(), fireworkViewItem.getPrice(),
                            fireworkViewItem.isHandicapAccess(), fireworkViewItem.getDuration(),
                            fireworkViewItem.getCrowded(), fireworkers,
                            fireworkViewItem.getParkings());

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
