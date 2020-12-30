package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

public interface InfoFireworkActionInterface {
    public void onClickEdit(FireworkModel fireworkModel);
    public void onFav(int id);
}
