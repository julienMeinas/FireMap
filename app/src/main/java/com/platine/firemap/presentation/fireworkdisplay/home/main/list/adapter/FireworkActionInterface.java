package com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter;

import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

public interface FireworkActionInterface {
    public void onInfoClicked(String address, String date, int price,
                              boolean accessHandicap, String crowed, List<Parking> parkings,
                              Fireworker fireworker);
}
