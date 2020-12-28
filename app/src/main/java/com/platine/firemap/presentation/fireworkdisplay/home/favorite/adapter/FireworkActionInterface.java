package com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter;

import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

public interface FireworkActionInterface {
    public void removeFavorite(int id);
    public void onInfoClicked(int id, String address,
                              String date, int price,
                              boolean accessHandicap, int duration,
                              String crowed, Fireworker fireworker,
                              List<Parking> parkings);
}
