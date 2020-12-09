package com.platine.firemap.data.repository.fireworkdisplay;

import com.platine.firemap.data.api.model.FireworkResponse;
import com.platine.firemap.data.repository.fireworkdisplay.remote.FireworkDisplayRemoteDataSource;

import io.reactivex.Single;

public class FireworkDisplayDataRepository {
    private FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource;

    public FireworkDisplayDataRepository(FireworkDisplayRemoteDataSource m_fireworkDisplayRemoteDataSource) {
        this.m_fireworkDisplayRemoteDataSource = m_fireworkDisplayRemoteDataSource;
    }

    /**
     * @return : get all fireworks
     */
    public Single<FireworkResponse> getFireworks() {
        return this.m_fireworkDisplayRemoteDataSource.getFireworks();
    }
}
