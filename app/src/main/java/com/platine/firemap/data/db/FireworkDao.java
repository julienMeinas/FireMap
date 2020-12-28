package com.platine.firemap.data.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import com.platine.firemap.data.entity.FireworkEntity;

@Dao
public interface FireworkDao {

    /**
     * @return all articles in database
     */
    @Query("SELECT * from fireworkentity")
    Flowable<List<FireworkEntity>> getFavorites();

    /**
     * @param fireworkEntity : article
     * @return insert new firework in data base
     */
    @Insert
    public Completable addFireworkToFavorites(FireworkEntity fireworkEntity);

    /**
     * @param id : id of firework
     * @return delete firework with the id from data base
     */
    @Query("DELETE FROM fireworkentity WHERE id = :id")
    public Completable deleteFireworkFromFavorites(int id);

    /**
     * @return all id of all firework in data base
     */
    @Query("SELECT id from fireworkentity")
    Single<List<Integer>> getFavoriteIdList();

}
