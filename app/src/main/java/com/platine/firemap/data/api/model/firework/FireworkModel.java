package com.platine.firemap.data.api.model.firework;

import java.io.Serializable;
import java.util.List;

public class FireworkModel implements Serializable {
    private int id;
    private double longitude;
    private double latitude;
    private String address;
    private String date;
    private int price;
    private boolean handicapAccess;
    private String duration;
    private String crowded;
    private Fireworker fireworker;
    private List<Parking> parking;

    public FireworkModel(int id, String address, String date, int price, boolean handicAccess, String duration, String crowded, Fireworker fireworker, List<Parking> parkings) {
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

    public FireworkModel() {
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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

    public String getDuration() {
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

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public void setDuration(String duration) {
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

    @Override
    public String toString() {
        String parking = "[";
        for(Parking p : getParking()) {
            parking += p.toString();
            parking += ", ";
        }
        parking += "]";

        return "FireworkModel{" +
                "'id'=" + getId() +
                ", 'longitude'=" + getLongitude() +
                ", 'latitude'=" + getLatitude() +
                ", 'address'=" + getAddress() +
                ", 'date'='" + getAddress() + '\'' +
                ", 'price'=" + getPrice() +
                ", 'handicapAccess'=" + isHandicAccess() +
                ", 'duration'=" + getDuration() +
                ", 'crowded'='" + getCrowded() + '\'' +
                ", 'fireworker'='" + getFireworker().toString() + '\'' +
                ", 'parking'='" + parking + '\'' +
                '}';
    }
}
