package com.platine.firemap;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.platine.firemap.data.di.FakeDependencyInjection;

public class FireMap extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }

}
