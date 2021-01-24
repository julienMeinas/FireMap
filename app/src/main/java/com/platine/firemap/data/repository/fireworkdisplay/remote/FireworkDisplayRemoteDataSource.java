package com.platine.firemap.data.repository.fireworkdisplay.remote;

import com.platine.firemap.data.api.FireworkDisplayService;
import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;

public class FireworkDisplayRemoteDataSource {
    private FireworkDisplayService m_fireworkDisplayService;

    public FireworkDisplayRemoteDataSource(FireworkDisplayService m_fireworkDisplayService) {
        this.m_fireworkDisplayService = m_fireworkDisplayService;
    }


    /**
     * @return : get all fireworks
     */
    public Flowable<List<FireworkModel>> getFireworks() {
        return this.m_fireworkDisplayService.getAllFireworks();
    }

    public Single<FireworkModel> getFireworkById(int id) {
        return this.m_fireworkDisplayService.getFireworkById(id);
    }

    public Flowable<List<FireworkerDetail>> getFireworkers() {
        return this.m_fireworkDisplayService.getAllFireworkers();
    }

    public Single<FireworkerDetail> getFireworkerById(int id) {
        return this.m_fireworkDisplayService.getFireworkerById(id);
    }

    /**
     * add new firework
     */
    public Call<FireworkModel> addFirework(FireworkModel firework) {
        return this.m_fireworkDisplayService.addFirework(firework);
    }

    /**
     * update firework
     */
    public Call<FireworkModel> updateFirework(int id, int price, boolean accessHandicap, String duration, String crowed) {
        return this.m_fireworkDisplayService.updateFirework(id, price, accessHandicap, duration, crowed);
    }

    public Call<FireworkModel> addParking(int id, String name, int price) {
        return this.m_fireworkDisplayService.addParking(id, name, price);
    }


    public Flowable<List<FireworkModel>> getAllFireworksByCity(String city) {
        return this.m_fireworkDisplayService.getAllFireworksByCity(city);
    }
}
