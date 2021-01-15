package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.mapper;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter.FireworkViewItem;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.adapter.FireworkerAvisViewItem;

import java.util.ArrayList;
import java.util.List;

public class AvisToViewItemMapper {

    public FireworkerAvisViewItem map(Avis avis) {
        FireworkerAvisViewItem fireworkerAvisViewItem = new FireworkerAvisViewItem();
        fireworkerAvisViewItem.setComment(avis.getComment());
        fireworkerAvisViewItem.setNote(avis.getNote());
        fireworkerAvisViewItem.setTitle(avis.getTitle());
        return fireworkerAvisViewItem;
    }


    public List<FireworkerAvisViewItem> map(List<Avis> avisList) {
        List<FireworkerAvisViewItem> avisViewModelList = new ArrayList<>();
        for (Avis avis : avisList) {
            avisViewModelList.add(map(avis));
        }
        return avisViewModelList;
    }

}