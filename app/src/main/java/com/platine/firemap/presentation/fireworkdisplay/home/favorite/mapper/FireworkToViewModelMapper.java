package com.platine.firemap.presentation.fireworkdisplay.home.favorite.mapper;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;

import java.util.ArrayList;
import java.util.List;

public class FireworkToViewModelMapper {

    public FireworkViewItem map(FireworkModel firework) {
        FireworkViewItem fireworkViewModel = new FireworkViewItem();
        fireworkViewModel.setId(firework.getId());
        fireworkViewModel.setCity(firework.getCity());
        fireworkViewModel.setAddress(firework.getAddress());
        fireworkViewModel.setDate(convertJsonToStringDate(firework.getDate()));
        fireworkViewModel.setDescription(firework.getDescription());
        fireworkViewModel.setPrice(firework.getPrice());
        fireworkViewModel.setHandicapAccess(firework.isHandicAccess());
        fireworkViewModel.setDuration(firework.getDuration());
        fireworkViewModel.setDuration(firework.getDuration());
        fireworkViewModel.setCrowded(firework.getCrowded());
        fireworkViewModel.setIdFireworker(firework.getIdFireworker());
        fireworkViewModel.setParkings(firework.getParking());
        fireworkViewModel.setAvis(firework.getAvis());
        fireworkViewModel.setNote(firework.getNote());
        return fireworkViewModel;
    }

    public List<FireworkViewItem> map(List<FireworkModel> fireworkList) {
        List<FireworkViewItem> fireworkViewModelList = new ArrayList<>();
        for (FireworkModel firework : fireworkList) {
            fireworkViewModelList.add(map(firework));
        }
        return fireworkViewModelList;
    }

    private String convertJsonToStringDate(String date) {
        return date.substring(8,10)+"/"+date.substring(5,7)+"/"+date.substring(0,4) + " - " + date.substring(11, 16);
    }

}
