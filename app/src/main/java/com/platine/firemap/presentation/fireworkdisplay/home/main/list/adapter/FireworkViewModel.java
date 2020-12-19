package com.platine.firemap.presentation.fireworkdisplay.home.main.list.adapter;

import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

public class FireworkViewModel {
    private String address;
    private String date;
    private int price;
    private boolean handicapAccess;
    private int duration;
    private String crowded;
    private Fireworker fireworker;
    private List<Parking> parkings;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHandicapAccess() {
        return handicapAccess;
    }

    public int getDuration() {
        return duration;
    }

    public String getCrowded() {
        return crowded;
    }

    public Fireworker getFireworker() {
        return fireworker;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHandicapAccess(boolean handicAccess) {
        this.handicapAccess = handicAccess;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCrowded(String crowded) {
        this.crowded = crowded;
    }

    public void setFireworker(Fireworker fireworker) {
        this.fireworker = fireworker;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }
}
