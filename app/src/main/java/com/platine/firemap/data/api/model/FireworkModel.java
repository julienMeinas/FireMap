package com.platine.firemap.data.api.model;

import java.util.Date;
import java.util.List;

public class FireworkModel {
    private int id;
    private String adress;
    private String date;
    private int price;
    private boolean handicAccess;
    private int duration;
    private String crowded;
    private Fireworker fireworker;
    private List<Parking> parkings;

    public FireworkModel(int id, String adress, String date, int price, boolean handicAccess, int duration, String crowded, Fireworker fireworker, List<Parking> parkings) {
        this.id = id;
        this.adress = adress;
        this.date = date;
        this.price = price;
        this.handicAccess = handicAccess;
        this.duration = duration;
        this.crowded = crowded;
        this.fireworker = fireworker;
        this.parkings = parkings;
    }

    public int getId() {
        return id;
    }

    public String getAdress() {
        return adress;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public boolean isHandicAccess() {
        return handicAccess;
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
        return parkings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHandicAccess(boolean handicAccess) {
        this.handicAccess = handicAccess;
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
        this.parkings = parkings;
    }
}
