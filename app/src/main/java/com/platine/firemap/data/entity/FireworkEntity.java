package com.platine.firemap.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FireworkEntity {
    @NonNull
    @PrimaryKey
    public int id;
}
