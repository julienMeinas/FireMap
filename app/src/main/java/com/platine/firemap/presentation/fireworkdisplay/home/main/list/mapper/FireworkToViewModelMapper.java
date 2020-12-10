package com.platine.firemap.presentation.fireworkdisplay.home.main.list.mapper;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter.FireworkViewModel;

import java.util.ArrayList;
import java.util.List;

public class FireworkToViewModelMapper {

    private FireworkViewModel map(FireworkModel firework) {
        FireworkViewModel fireworkViewModel = new FireworkViewModel();
        fireworkViewModel.setAddress(firework.getAddress());
        fireworkViewModel.setDate(firework.getDate());
        return fireworkViewModel;
    }

    public List<FireworkViewModel> map(List<FireworkModel> fireworkList) {
        List<FireworkViewModel> fireworkViewModelList = new ArrayList<>();
        for (FireworkModel firework : fireworkList) {
            fireworkViewModelList.add(map(firework));
        }
        return fireworkViewModelList;
    }

}
