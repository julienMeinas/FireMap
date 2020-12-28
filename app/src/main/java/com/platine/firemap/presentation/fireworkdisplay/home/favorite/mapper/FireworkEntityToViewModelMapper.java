package com.platine.firemap.presentation.fireworkdisplay.home.favorite.mapper;

import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;

import java.util.ArrayList;
import java.util.List;

public class FireworkEntityToViewModelMapper {

    public FireworkViewItem map(FireworkEntity fireworkEntity) {
        FireworkViewItem fireworkViewModel = new FireworkViewItem();
        fireworkViewModel.setId(fireworkEntity.getId());
        return fireworkViewModel;
    }


    public List<FireworkViewItem> map(List<FireworkEntity> fireworkEntities) {
        List<FireworkViewItem> fireworkViewModels = new ArrayList<>();
        for(FireworkEntity fireworkEntity : fireworkEntities) {
            fireworkViewModels.add(map(fireworkEntity));
        }
        return  fireworkViewModels;
    }
}
