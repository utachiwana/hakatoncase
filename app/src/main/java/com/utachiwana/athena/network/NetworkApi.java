package com.utachiwana.athena.network;

import com.google.gson.JsonObject;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.data.Prefs;
import com.utachiwana.athena.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkApi {

    @POST("user/register")
    Call<JsonObject> registration(@Body User body);

    @GET("user/login")
    Call<JsonObject> authorization(@Query("email") String email, @Query("password") String pass);

    @GET("time/make")
    Call<String> newFreeTime(@Query("token") String token,
                                  @Query("day") String day,
                                  @Query("timeStart") String timeStart,
                                  @Query("timeEnd") String timeEnd);


}
