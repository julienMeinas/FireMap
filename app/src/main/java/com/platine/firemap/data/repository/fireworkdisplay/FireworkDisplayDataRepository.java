package com.platine.firemap.data.repository.fireworkdisplay;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;
import com.platine.firemap.data.entity.FireworkEntity;
import com.platine.firemap.data.repository.fireworkdisplay.local.FireworkDisplayLocalDataSource;
import com.platine.firemap.data.repository.fireworkdisplay.remote.FireworkDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;

public class FireworkDisplayDataRepository {
    private FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource;
    private FireworkDisplayLocalDataSource fireworkDisplayLocalDataSource;

    public FireworkDisplayDataRepository(FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource,
                                         FireworkDisplayLocalDataSource fireworkDisplayLocalDataSource) {
        this.m_fireworkDisplayRemoteDataSource = m_fireworkDisplayRemoteDataSource;
        this.fireworkDisplayLocalDataSource = fireworkDisplayLocalDataSource;
    }

    /**
     * @return : get all fireworks
     */
    public Flowable<List<FireworkModel>> getFireworks() {
        return this.m_fireworkDisplayRemoteDataSource.getFireworks();
    }

    public Single<FireworkModel> getFireworkById(int id) {
        return this.m_fireworkDisplayRemoteDataSource.getFireworkById(id);
    }

    public Flowable<List<FireworkerDetail>> getFireworkers() {
        return this.m_fireworkDisplayRemoteDataSource.getFireworkers();
    }

    public Single<FireworkerDetail> getFireworkerById(int id) {
        return this.m_fireworkDisplayRemoteDataSource.getFireworkerById(id);
    }

    /**
     * add new firework
     */
    public Call<FireworkModel> addFirework(FireworkModel firework) {
        return this.m_fireworkDisplayRemoteDataSource.addFirework(firework);
    }

    /**
     * update firework
     */
    public Call<FireworkModel> updateFirework(int id, int price, boolean accessHandicap, String duration, String crowed) {
        return this.m_fireworkDisplayRemoteDataSource.updateFirework(id, price, accessHandicap, duration, crowed);
    }

    public Call<FireworkModel> addParking(int id, String name, int price) {
        return this.m_fireworkDisplayRemoteDataSource.addParking(id, name, price);
    }

    /**
     * @return all firework in favorite data base
     */
    public Flowable<List<FireworkEntity>> getFavorites() {
        return fireworkDisplayLocalDataSource.getFavorites();
    }


    /**
     * @param fireworkEntity : firework
     * @return : add firework in data base
     */
    public Completable addFireworkToFavorites(FireworkEntity fireworkEntity) {
        return fireworkDisplayLocalDataSource.addFireworkToFavorites(fireworkEntity);
    }

    /**
     * @param fireworkId : id of a firework
     * @return : remove firework in data base with the id
     */
    public Completable removeFireworkFromFavorites(int fireworkId) {
        return fireworkDisplayLocalDataSource.deleteFireworkFromFavorites(fireworkId);
    }
}
