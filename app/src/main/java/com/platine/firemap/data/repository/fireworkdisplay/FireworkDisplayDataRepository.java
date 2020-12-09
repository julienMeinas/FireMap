package com.platine.firemap.data.repository.fireworkdisplay;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;
import com.platine.firemap.data.repository.fireworkdisplay.remote.FireworkDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Flowable;

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
}
