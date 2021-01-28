package com.platine.firemap.presentation.Ressources;

import android.widget.ImageView;
import android.widget.TextView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.List;

public class Utils {
    public static String convertJsonToStringDate(String date) {
        return date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4) + " - " + date.substring(11, 16);
    }


    public static void setComponent(String city, TextView textViewCity,
                                    String address, TextView textViewPlace,
                                    String date, TextView textViewDate,
                                    int price, ImageView imagePrice,
                                    boolean accessHandicap, ImageView imageAccessHandicap,
                                    String duration, ImageView imageDuration,
                                    String crowed, ImageView imagePeople,
                                    List<Parking> parkings, ImageView imageParking,
                                    List<Fireworker> fireworker, TextView textViewFireworker) {
        textViewCity.setText(city);
        // address
        textViewPlace.setText(address);
        // date
        textViewDate.setText(convertJsonToStringDate(date));
        //price
        if(price == 0) {
            imagePrice.setImageResource(R.drawable.drawable_price_free);
        } else if(price < 50000) {
            imagePrice.setImageResource(R.drawable.drawable_price_no_free);
        }
        else{
            imagePrice.setImageResource(R.drawable.drawable_empty_price);
        }
        //parking
        imageParking.setImageResource(R.drawable.drawable_parking_free);
        // access handicap
        imageAccessHandicap.setImageResource(accessHandicap ? R.drawable.drawable_handicap_access : R.drawable.drawable_no_handicap_access);
        // duration
        if(duration.equals("Short")){
            imageDuration.setImageResource(R.drawable.drawable_duration_short);
        }else if(duration.equals("Middle")){
            imageDuration.setImageResource(R.drawable.drawable_duration_middle);
        }else if (duration.equals("Long")){
            imageDuration.setImageResource(R.drawable.drawable_duration_long);
        } else {
            imageDuration.setImageResource(R.drawable.drawable_empty_duration);
        }
        // crowed
        if(crowed.equals("Low")) {
            imagePeople.setImageResource(R.drawable.drawable_people_low);
        }else if(crowed.equals("Medium")) {
            imagePeople.setImageResource(R.drawable.drawable_people_medium);
        }else if(crowed.equals("High")){
            imagePeople.setImageResource(R.drawable.drawable_people_high);
        } else {
            imagePeople.setImageResource(R.drawable.drawable_empty_people);
        }
        // parking
        if(parkings.size() == 0) {
            imageParking.setImageResource(R.drawable.drawable_no_parking);
        }else{
            boolean freeParking = false;
            for(Parking p : parkings) {
                if(p.getPrice() == 0){
                    imageParking.setImageResource(R.drawable.drawable_parking_free);
                    freeParking = true;
                }
            }
            if(!freeParking) {
                imageParking.setImageResource(R.drawable.drawable_parking_no_free);
            }
        }
        // fireworker
        textViewFireworker.setText(fireworker.get(0).getName());
    }
}
