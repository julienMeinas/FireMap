package com.platine.firemap.presentation.fireworkdisplay.infoFirework;

import com.platine.firemap.data.api.model.firework.FireworkModel;

public interface InfoFireworkActionInterface {
    public void onClickEdit(FireworkModel fireworkModel);
    public void onFav(int id);
    public void onProfile(int id);
}
