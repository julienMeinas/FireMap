package com.platine.firemap.data.api.model;

import java.util.List;

public class FireworkResponse {
    private List<FireworkModel> fireworks;

    public FireworkResponse(List<FireworkModel> fireworks) {
        this.fireworks = fireworks;
    }

    public List<FireworkModel> getFireworks() {
        return fireworks;
    }

    public void setFireworks(List<FireworkModel> fireworks) {
        this.fireworks = fireworks;
    }
}
