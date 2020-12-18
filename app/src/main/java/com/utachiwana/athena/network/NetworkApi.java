package com.utachiwana.athena.network;

import com.google.gson.JsonObject;
import com.utachiwana.athena.data.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkApi {

    @POST("registration")
    JsonObject registration(@Body User body);

    @GET("auth")
    JsonObject authorization(@Query("name") String name, @Query("pass") String pass);

    @GET("entries")
    JsonObject getEntries(@Query("type") String type);

    @GET("entry")
    JsonObject getEntry(@Query("entry_id") String id);

    @GET("my_entries")
    JsonObject getMyEntries(@Query("user_id") String user_id);

    @GET("delete_entry")
    JsonObject deleteEntry(@Query("entry_id") String entry_id);

    @GET("chat")
    JsonObject getChat(@Query("user_id") String user_id, @Query("guest_id") String guest_id);

}
