package com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter;

import com.platine.firemap.data.api.model.firework.FireworkModel;

public interface FireworkActionInterface {
    public void removeFavorite(int id);
    public void onInfoClicked(FireworkModel fireworkModel);
}
