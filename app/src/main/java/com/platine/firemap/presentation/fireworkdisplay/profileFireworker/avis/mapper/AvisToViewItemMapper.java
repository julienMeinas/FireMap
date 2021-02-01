package com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.mapper;

import com.platine.firemap.data.api.model.fireworker.Avis;
import com.platine.firemap.presentation.fireworkdisplay.profileFireworker.avis.adapter.FireworkerAvisViewItem;

import java.util.ArrayList;
import java.util.List;

public class AvisToViewItemMapper {

    public FireworkerAvisViewItem map(Avis avis) {
        FireworkerAvisViewItem fireworkerAvisViewItem = new FireworkerAvisViewItem();
        fireworkerAvisViewItem.setComment(avis.getComment());
        fireworkerAvisViewItem.setNote(avis.getNote());
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
