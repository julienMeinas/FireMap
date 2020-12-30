package com.platine.firemap.data.repository.fireworkdisplay.local;

import com.platine.firemap.data.db.FireworkDatabase;
import com.platine.firemap.data.entity.FireworkEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class FireworkDisplayLocalDataSource {
    private FireworkDatabase fireworkDatabase;

    public FireworkDisplayLocalDataSource (FireworkDatabase fireworkDatabase) {
        this.fireworkDatabase = fireworkDatabase;
    }

    /**
     * @return the fireworks in local dada base
     */
    public Flowable<List<FireworkEntity>> getFavorites() {
        return fireworkDatabase.fireworkDao().getFavorites();
    }

    /**
     * @param fireworkEntity : object articles
     * @return : add firework in data base
     */
    public Completable addFireworkToFavorites(FireworkEntity fireworkEntity) {
        return fireworkDatabase.fireworkDao().addFireworkToFavorites(fireworkEntity);
    }

    /**
     * @param id : title of the firework to be deleted
     * @return : remove article with the title
     */
    public Completable deleteFireworkFromFavorites(int id) {
        return fireworkDatabase.fireworkDao().deleteFireworkFromFavorites(id);
    }

    /**
     * @return : get all id of fireworks in data base
     */
    public Single<List<Integer>> getFavoriteTitleList() {
        return fireworkDatabase.fireworkDao().getFavoriteIdList();
    }
}
