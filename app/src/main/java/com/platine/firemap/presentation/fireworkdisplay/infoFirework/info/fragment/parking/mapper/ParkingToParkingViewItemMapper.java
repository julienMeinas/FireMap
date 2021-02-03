package com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.parking.mapper;

import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.presentation.fireworkdisplay.infoFirework.info.fragment.parking.adapter.ParkingViewItem;

import java.util.ArrayList;
import java.util.List;

public class ParkingToParkingViewItemMapper {
    public ParkingViewItem map(Parking parking) {
        ParkingViewItem parkingViewItem = new ParkingViewItem();
        parkingViewItem.setName(parking.getName());
        parkingViewItem.setPrice(parking.getPrice());
        return parkingViewItem;
    }

    public List<ParkingViewItem> map(List<Parking> parkings) {
        List<ParkingViewItem> parkingViewItems = new ArrayList<>();
        for(Parking parking : parkings) {
            parkingViewItems.add(map(parking));
        }
        return parkingViewItems;
    }
}
