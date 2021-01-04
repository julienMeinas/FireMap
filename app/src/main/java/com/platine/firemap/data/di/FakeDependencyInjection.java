package com.platine.firemap.data.di;

import android.content.Context;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.platine.firemap.data.api.FireworkDisplayService;
import com.platine.firemap.data.db.FireworkDatabase;
import com.platine.firemap.data.repository.fireworkdisplay.FireworkDisplayDataRepository;
import com.platine.firemap.data.repository.fireworkdisplay.local.FireworkDisplayLocalDataSource;
import com.platine.firemap.data.repository.fireworkdisplay.remote.FireworkDisplayRemoteDataSource;
import com.platine.firemap.presentation.viewmodel.ViewModelFactory;
import com.platine.firemap.presentation.viewmodel.ViewModelFavoriteFactory;
import com.platine.firemap.presentation.viewmodel.ViewModelFireworkerFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {
    private static Retrofit retrofit;
    private static Gson gson;
    private static ViewModelFactory viewModelFactory;
    private static ViewModelFavoriteFactory viewModelFavoriteFactory;
    private static ViewModelFireworkerFactory viewModelFireworkerFactory;
    private static FireworkDisplayService fireworkDisplayService;
    private static FireworkDisplayDataRepository fireworkDisplayDataRepository;
    private static Context applicationContext;
    private static FireworkDatabase fireworkDatabase;


    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getArticleDisplayRepository());
        }
        return viewModelFactory;
    }

    public static ViewModelFavoriteFactory getViewModelFavoriteFactory() {
        if (viewModelFavoriteFactory == null) {
            viewModelFavoriteFactory = new ViewModelFavoriteFactory(getArticleDisplayRepository());
        }
        return viewModelFavoriteFactory;
    }

    public static ViewModelFireworkerFactory getViewModelFireworkerFactory() {
        if (viewModelFireworkerFactory == null) {
            viewModelFireworkerFactory = new ViewModelFireworkerFactory(getArticleDisplayRepository());
        }
        return viewModelFireworkerFactory;
    }

    public static FireworkDisplayDataRepository getArticleDisplayRepository() {
        if (fireworkDisplayDataRepository == null) {
            fireworkDisplayDataRepository = new FireworkDisplayDataRepository(
                                                    new FireworkDisplayRemoteDataSource(getArticleDisplayService()),
                                                    new FireworkDisplayLocalDataSource(getFireworkDatabase())
            );
        }
        return fireworkDisplayDataRepository;
    }


    public static FireworkDisplayService getArticleDisplayService() {
        if (fireworkDisplayService == null) {
            fireworkDisplayService = getRetrofit().create(FireworkDisplayService.class);
        }
        return fireworkDisplayService;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://firemap-api-rest.herokuapp.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }


    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }


    public static FireworkDatabase getFireworkDatabase() {
        if (fireworkDatabase == null) {
            fireworkDatabase = Room.databaseBuilder(applicationContext,
                    FireworkDatabase.class, "firework-database").build();
        }
        return fireworkDatabase;
    }


}
