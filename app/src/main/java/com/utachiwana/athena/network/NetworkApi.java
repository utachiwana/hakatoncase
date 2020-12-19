package com.utachiwana.athena.network;

import com.google.gson.JsonObject;
import com.utachiwana.athena.data.Post;
import com.utachiwana.athena.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkApi {

    @POST("user/register")
    Call<JsonObject> registration(@Body User body);

    @GET("auth")
    Call<JsonObject> authorization(@Query("name") String name, @Query("pass") String pass);

    @GET("entries")
    Call<List<Post>> getPosts(@Query("filters") Boolean... args);

    @GET("entry")
    JsonObject getEntry(@Query("entry_id") String id);

    @GET("my_entries")
    JsonObject getMyEntries(@Query("user_id") String user_id);

    @GET("delete_entry")
    JsonObject deleteEntry(@Query("entry_id") String entry_id);

    @GET("chat")
    JsonObject getChat(@Query("user_id") String user_id, @Query("guest_id") String guest_id);

}
