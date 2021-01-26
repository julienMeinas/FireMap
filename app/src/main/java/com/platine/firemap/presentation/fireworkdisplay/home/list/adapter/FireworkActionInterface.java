package com.platine.firemap.presentation.fireworkdisplay.home.list.adapter;

import com.platine.firemap.data.api.model.firework.FireworkModel;

public interface FireworkActionInterface {
    public void onInfoClicked(FireworkModel fireworkModel);
    public void addFirework();
    public void nextFireworksOn();
    public void nextFireworksOff();
}
