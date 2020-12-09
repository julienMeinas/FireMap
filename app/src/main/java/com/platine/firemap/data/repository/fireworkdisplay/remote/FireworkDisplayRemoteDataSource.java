package com.platine.firemap.data.repository.fireworkdisplay.remote;

import com.platine.firemap.data.api.FireworkDisplayService;
import com.platine.firemap.data.api.model.FireworkResponse;

import io.reactivex.Single;

public class FireworkDisplayRemoteDataSource {
    private FireworkDisplayService m_fireworkDisplayService;

    public FireworkDisplayRemoteDataSource(FireworkDisplayService m_fireworkDisplayService) {
        this.m_fireworkDisplayService = m_fireworkDisplayService;
    }

    /**
     * @return : get all bests articles
     */
    public Single<FireworkResponse> getBestsArticles() {
        return this.m_fireworkDisplayService.getAllFireworks();
    }
}
