package com.platine.firemap.data.repository.fireworkdisplay.remote;

import com.platine.firemap.data.api.FireworkDisplayService;
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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

    /**
     * add new firework
     */
    public Call<FireworkModel> addFirework(FireworkModel firework) {
        return this.m_fireworkDisplayService.addFirework(firework);
    }

    /**
     * update firework
     */
    public Call<FireworkModel> updateFirework(int id, int price, boolean accessHandicap, int duration, String crowed) {
        return this.m_fireworkDisplayService.updateFirework(id, price, accessHandicap, duration, crowed);
    }
}
