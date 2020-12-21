package com.platine.firemap.data.api;

import com.platine.firemap.data.api.model.FireworkModel;
import com.platine.firemap.data.api.model.FireworkResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Classes that call uses the news API
 */
public interface FireworkDisplayService {
    /**
     * Method to retrieve popular fireworks
     *
     * @return popular fireworks
     */
    @GET("https://firemap-api-rest.herokuapp.com/fireworks/")
    Flowable<List<FireworkModel>> getAllFireworks();


    /**
     * Method to POST firewotk
     */
    @POST("http://firemap-api-rest.herokuapp.com/fireworks/")
    Call<FireworkModel> addFirework(@Body FireworkModel posts);
}
