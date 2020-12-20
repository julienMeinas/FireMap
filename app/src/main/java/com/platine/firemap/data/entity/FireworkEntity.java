package com.platine.firemap.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.platine.firemap.data.api.model.Fireworker;
import com.platine.firemap.data.api.model.Parking;

import java.util.List;

@Entity
public class FireworkEntity {
    @NonNull
    @PrimaryKey
    public int id;
    public String address;
    public String date;
    public int price;
    public List<Parking> parkings;
    public boolean accessHandicap;
    public String crowed;
    public Fireworker fireworker;

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

    public List<Parking> getParkings() {
        return parkings;
    }

    public boolean isAccessHandicap() {
        return accessHandicap;
    }

    public String getCrowed() {
        return crowed;
    }

    public Fireworker getFireworker() {
        return fireworker;
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

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }

    public void setAccessHandicap(boolean accessHandicap) {
        this.accessHandicap = accessHandicap;
    }

    public void setCrowed(String crowed) {
        this.crowed = crowed;
    }

    public void setFireworker(Fireworker fireworker) {
        this.fireworker = fireworker;
    }
}
