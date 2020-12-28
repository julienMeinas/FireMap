package com.platine.firemap.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.platine.firemap.data.entity.FireworkEntity;

@Database(entities = {FireworkEntity.class}, version = 1)
public abstract class FireworkDatabase extends RoomDatabase {
    public abstract FireworkDao fireworkDao();
}
