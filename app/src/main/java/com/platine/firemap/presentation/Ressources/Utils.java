package com.platine.firemap.presentation.Ressources;

import android.widget.ImageView;
import android.widget.TextView;

import com.platine.firemap.R;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.presentation.viewmodel.ListViewModel;

import java.util.List;

public class Utils {
    public static final String msg_price_free = "Gratuit";
    public static final String msg_price_not_free = "Payant";
    public static final String msg_no_parking = "Pas de parking";
    public static final String msg_parking_free = "Parking gratuit";
    public static final String msg_parking_no_free = "Parking payant";
    public static final String msg_access_handicap = "Accès handicapé";
    public static final String msg_no_access_handicap = "Pas d'accès handicapé";
    public static final String msg_duration_short = "Court";
    public static final String msg_duration_middle = "Moyen";
    public static final String msg_duration_long = "Long";
    public static final String msg_crowed_low = "Peu";
    public static final String msg_crowed_medium = "Moyen";
    public static final String msg_crowed_high = "Beaucoup";
    public static final String msg_empty = "Non indiqué";


    public static String convertJsonToStringDate(String date) {
        return date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4) + " - " + date.substring(11, 16);
    }


    public static void setComponent(String city, TextView textViewCity,
                                    String address, TextView textViewPlace,
                                    String date, TextView textViewDate,
                                    int price, ImageView imagePrice, TextView textPrice,
                                    boolean accessHandicap, ImageView imageAccessHandicap, TextView textHandicap,
                                    String duration, ImageView imageDuration, TextView textDuration,
                                    String crowed, ImageView imagePeople, TextView textCrowed,
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
            textPrice.setText(msg_price_free);
        } else if(price < 50000) {
            imagePrice.setImageResource(R.drawable.drawable_price_no_free);
            textPrice.setText(msg_price_not_free);
        }
        else{
            imagePrice.setImageResource(R.drawable.drawable_empty_price);
            textPrice.setText(msg_empty);
        }
        //parking
        imageParking.setImageResource(R.drawable.drawable_parking_free);
        // access handicap
        if(accessHandicap) {
            imageAccessHandicap.setImageResource(R.drawable.drawable_handicap_access);
            textHandicap.setText(msg_access_handicap);
        }else {
            imageAccessHandicap.setImageResource(R.drawable.drawable_no_handicap_access);
            textHandicap.setText(msg_no_access_handicap);
        }
        // duration
        if(duration.equals("Short")){
            imageDuration.setImageResource(R.drawable.drawable_duration_short);
            textDuration.setText(msg_duration_short);
        }else if(duration.equals("Middle")){
            imageDuration.setImageResource(R.drawable.drawable_duration_middle);
            textDuration.setText(msg_duration_middle);
        }else if (duration.equals("Long")){
            imageDuration.setImageResource(R.drawable.drawable_duration_long);
            textDuration.setText(msg_duration_long);
        } else {
            imageDuration.setImageResource(R.drawable.drawable_empty_duration);
            textDuration.setText(msg_empty);
        }
        // crowed
        if(crowed.equals("Low")) {
            imagePeople.setImageResource(R.drawable.drawable_people_low);
            textCrowed.setText(msg_crowed_low);
        }else if(crowed.equals("Medium")) {
            imagePeople.setImageResource(R.drawable.drawable_people_medium);
            textCrowed.setText(msg_crowed_medium);
        }else if(crowed.equals("High")){
            imagePeople.setImageResource(R.drawable.drawable_people_high);
            textCrowed.setText(msg_crowed_high);
        } else {
            imagePeople.setImageResource(R.drawable.drawable_empty_people);
            textCrowed.setText(msg_empty);
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
