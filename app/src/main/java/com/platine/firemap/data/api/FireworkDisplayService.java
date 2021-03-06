package com.platine.firemap.data.api;

import com.platine.firemap.data.api.model.firework.FireworkModel;
import com.platine.firemap.data.api.model.firework.Fireworker;
import com.platine.firemap.data.api.model.firework.Parking;
import com.platine.firemap.data.api.model.fireworker.FireworkerDetail;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Classes that call uses the news API
 */
public interface FireworkDisplayService {


    @GET("https://firemap-api-rest.herokuapp.com/fireworks")
    Flowable<List<FireworkModel>> getAllFireworks();

    /**
     * Method to retrieve popular fireworks
     *
     * @return popular fireworks
     */
    @GET("https://firemap-api-rest.herokuapp.com/fireworks/search")
    Flowable<List<FireworkModel>> getAllFireworksWithSearch(@Query("city") String city);

    @GET("https://firemap-api-rest.herokuapp.com/fireworks/search/future")
    Flowable<List<FireworkModel>> getAllFireworksFutureWithSearch(@Query("city") String city);

    /**
     * @param id of firework
     * @return firework
     */
    @GET("https://firemap-api-rest.herokuapp.com/fireworks/{id}")
    Single<FireworkModel> getFireworkById(@Path("id") int id);


    /**
     * @return all fireworker
     */
    @GET("https://firemap-api-rest.herokuapp.com/fireworkers/")
    Flowable<List<FireworkerDetail>> getAllFireworkers();

    /**
     * @param id
     * @return fireworker with id
     */
    @GET("https://firemap-api-rest.herokuapp.com/fireworkers/{id}")
    Single<FireworkerDetail> getFireworkerById(@Path("id") int id);


    /**
     * Method to POST firewotk
     */
    @POST("http://firemap-api-rest.herokuapp.com/fireworks/")
    Call<FireworkModel> addFirework(@Body FireworkModel posts);

    /**
     * @param id : id of the firework
     * @return update of the firework
     */
    @Headers({"Content-Type: application/json"})
    @PUT("https://firemap-api-rest.herokuapp.com/fireworks/{id}")
    Call<FireworkModel> updateFirework(@Path("id") int id, @Query("price") int price, @Query("accessHandicap") boolean accessHandicap,
                                       @Query("duration") String duration, @Query("crowed") String crowed);


    @PUT("http://firemap-api-rest.herokuapp.com/fireworks/addParking/{id}")
    Call<FireworkModel> addParking(@Path("id") int id, @Query("name") String name ,@Query("price") int price);


    @GET("http://firemap-api-rest.herokuapp.com/fireworks/filterByCity")
    Flowable<List<FireworkModel>> getAllFireworksByCity(@Query("city") String city);

    @FormUrlEncoded
    @POST("http://firemap-api-rest.herokuapp.com/email")
    Call<Object> sendEmail(@Field("body") String message, @Field("subject") String subject);

    @PUT("https://firemap-api-rest.herokuapp.com/fireworkers/addAvis/{id}")
    Call<FireworkerDetail> addAvis(@Path("id") int id, @Query("note") double note,
                                   @Query("comment") String comment);

    @PUT("https://firemap-api-rest.herokuapp.com/fireworks/addAvis/{id}")
    Call<FireworkModel> addAvisFirework(@Path("id") int id, @Query("note") double note,
                                   @Query("comment") String comment);


    @GET("http://firemap-api-rest.herokuapp.com/fireworkers/byCity/{id}")
    Flowable<List<FireworkModel>> getAllFireworksByCityByFireworker(@Path("id") int id, @Query("city") String city);

}
