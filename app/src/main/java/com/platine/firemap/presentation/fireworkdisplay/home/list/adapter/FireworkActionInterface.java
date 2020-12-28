package com.platine.firemap.presentation.fireworkdisplay.home.list.adapter;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

public interface FireworkActionInterface {
    public void onInfoClicked(FireworkModel fireworkModel);
    public void onFav(int id);
}
