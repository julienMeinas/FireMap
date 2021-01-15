package com.platine.firemap.presentation.fireworkdisplay.addFirework.mapper;

import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.presentation.fireworkdisplay.addFirework.adapter.Fireworker_item;

import java.util.ArrayList;
import java.util.List;

public class FireworkerDetailleToFireworkerItemMapper {

    public Fireworker_item map(FireworkerDetail fireworkerDetail) {
        Fireworker_item fireworker_item = new Fireworker_item();
        fireworker_item.setName(fireworkerDetail.getName());
        fireworker_item.setId(fireworkerDetail.getId());
        fireworker_item.setAvis(fireworkerDetail.getAvis());
        return fireworker_item;
    }

    public List<Fireworker_item> map(List<FireworkerDetail> fireworkerDetails) {
        List<Fireworker_item> fireworker_items = new ArrayList<>();
        for(FireworkerDetail fireworkerDetail : fireworkerDetails) {
            fireworker_items.add(map(fireworkerDetail));
        }
        return fireworker_items;
    }
}
