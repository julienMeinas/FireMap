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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
