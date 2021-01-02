package com.platine.firemap.data.repository.fireworkdisplay.mapper;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.entity.FireworkEntity;

public class FireworkToFireworkEntityMapper {

    public FireworkEntity map(FireworkModel fireworkModel) {
        FireworkEntity fireworkEntity = new FireworkEntity();
        fireworkEntity.setId(fireworkModel.getId());
        return fireworkEntity;
    }
}
