package com.platine.firemap.presentation.fireworkdisplay.home.favorite.adapter;

import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;

import java.util.Date;
import java.util.List;

public class FireworkViewItem {
    private int id;
    private String city;
    private String address;
    private String date;
    private int price;
    private boolean handicapAccess;
    private String duration;
    private String crowded;
    private int idFireworker;
    private List<Parking> parkings;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public String getDuration() {
        return duration;
    }

    public String getCrowded() {
        return crowded;
    }

    public int getIdFireworker() {
        return idFireworker;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHandicapAccess(boolean handicAccess) {
        this.handicapAccess = handicAccess;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setCrowded(String crowded) {
        this.crowded = crowded;
    }

    public void setIdFireworker(int idFireworker) {
        this.idFireworker = idFireworker;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }
}
