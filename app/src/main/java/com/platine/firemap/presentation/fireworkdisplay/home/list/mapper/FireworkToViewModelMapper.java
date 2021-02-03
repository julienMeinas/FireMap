package com.platine.firemap.presentation.fireworkdisplay.home.list.mapper;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.presentation.Ressources.Utils;
import com.platine.firemap.presentation.fireworkdisplay.home.list.adapter.FireworkViewItem;

import java.util.ArrayList;
import java.util.List;

public class FireworkToViewModelMapper {

    private FireworkViewItem map(FireworkModel firework) {
        FireworkViewItem fireworkViewModel = new FireworkViewItem();
        fireworkViewModel.setId(firework.getId());
        fireworkViewModel.setLatitude(firework.getLatitude());
        fireworkViewModel.setLongitude(firework.getLongitude());
        fireworkViewModel.setCity(firework.getCity());
        fireworkViewModel.setAddress(firework.getAddress());
        fireworkViewModel.setDate(Utils.convertJsonToStringDate(firework.getDate()));
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



}
