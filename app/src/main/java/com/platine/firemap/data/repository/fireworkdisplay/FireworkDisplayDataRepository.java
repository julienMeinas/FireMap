package com.platine.firemap.data.repository.fireworkdisplay;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;
import com.platine.firemap.data.repository.fireworkdisplay.remote.FireworkDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class FireworkDisplayDataRepository {
    private FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource;

    public FireworkDisplayDataRepository(FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource) {
        this.m_fireworkDisplayRemoteDataSource = m_fireworkDisplayRemoteDataSource;
    }

    /**
     * @return : get all fireworks
     */
    public Flowable<List<FireworkModel>> getFireworks() {
        return this.m_fireworkDisplayRemoteDataSource.getFireworks();
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
    public Call<FireworkModel> updateFirework(int id, int price, boolean accessHandicap, int duration, String crowed) {
        return this.m_fireworkDisplayRemoteDataSource.updateFirework(id, price, accessHandicap, duration, crowed);
    }
}
