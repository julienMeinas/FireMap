package com.platine.firemap.data.api.model;

import java.util.Date;
import java.util.List;

public class FireworkModel {
    private int id;
    private String address;
    private String date;
    private int price;
    private boolean handicapAccess;
    private int duration;
    private String crowded;
    private Fireworker fireworker;
    private List<Parking> parking;

    public FireworkModel(int id, String address, String date, int price, boolean handicAccess, int duration, String crowded, Fireworker fireworker, List<Parking> parkings) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.price = price;
        this.handicapAccess = handicAccess;
        this.duration = duration;
        this.crowded = crowded;
        this.fireworker = fireworker;
        this.parking = parkings;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHandicAccess() {
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

    public List<Parking> getParking() {
        return parking;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHandicAccess(boolean handicAccess) {
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

    public void setParking(List<Parking> parkings) {
        this.parking = parkings;
    }
}
