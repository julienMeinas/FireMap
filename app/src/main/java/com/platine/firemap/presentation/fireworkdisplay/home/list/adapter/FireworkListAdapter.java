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
        private ImageView price;
        private ImageView parking;
        private ImageView accessHandicap;
        private ImageView people;
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
            parentLayout = v.findViewById(R.id.parent_layout);
            price = v.findViewById(R.id.price);
            parking = v.findViewById(R.id.parking);
            accessHandicap = v.findViewById(R.id.accessHandicap);
            people = v.findViewById(R.id.people);
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

            /**
            // price
            if(fireworkViewModel.getPrice() == 0) {
                price.setImageResource(R.drawable.drawable_price_free);
            } else if(fireworkViewModel.getPrice() < 50000) {
                price.setImageResource(R.drawable.drawable_price_no_free);
            }
            else{
                price.setImageResource(R.drawable.drawable_empty_price);
            }
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
            else if(fireworkViewModel.getCrowded().equals("High")){
                people.setImageResource(R.drawable.drawable_people_high);
            } else {
                people.setImageResource(R.drawable.drawable_empty_people);
            }
            */
            this.v.findViewById(R.id.parent_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Fireworker> fireworkers = new ArrayList<>();
                    fireworkers.add(fireworkViewModel.getFireworker());
                    FireworkModel fireworkModel = new FireworkModel(fireworkViewModel.getId(), fireworkViewModel.getCity(),
                                                                    fireworkViewModel.getAddress(),
                                                                    fireworkViewModel.getDate(), fireworkViewModel.getPrice(),
                                                                    fireworkViewModel.isHandicapAccess(), fireworkViewModel.getDuration(),
                                                                    fireworkViewModel.getCrowded(), fireworkers,
                                                                    fireworkViewModel.getParkings());

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
