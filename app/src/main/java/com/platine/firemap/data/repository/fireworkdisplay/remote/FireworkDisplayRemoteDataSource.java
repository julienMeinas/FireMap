package com.platine.firemap.data.repository.fireworkdisplay.remote;

import com.platine.firemap.data.api.FireworkDisplayService;
import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;

import java.util.List;

import io.reactivex.Flowable;
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

    /**
     * add new firework
     */
    public Call<FireworkModel> addFirework(FireworkModel firework) {
        return this.m_fireworkDisplayService.addFirework(firework);
    }

    /**
     * update firework
     */
    public Call<FireworkModel> updateFirework(FireworkModel firework, int id) {
        return this.m_fireworkDisplayService.updateFirework(firework, id);
    }
}
