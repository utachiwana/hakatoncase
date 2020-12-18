package com.utachiwana.athena.network;

import com.utachiwana.athena.BuildConfig;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static String ANDROID_ID = "android_id", SERVER_ID = "server_id";

    private static OkHttpClient mClient;
    private static Retrofit mRetrofit;
    private static NetworkApi mApi;

    public static OkHttpClient getClient() {
        if (mClient == null) {
            mClient = new OkHttpClient();
            mClient = mClient.newBuilder().authenticator(new Authenticator() {
                @Nullable
                @Override
                public Request authenticate(@Nullable Route route, Response response) throws IOException {
                    return response.request().newBuilder().addHeader(ANDROID_ID, "").addHeader(SERVER_ID, "").build();
                }
            }).build();
        }
        return mClient;
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.base_url)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static NetworkApi getApi() {
        if (mApi == null) {
            mApi = getRetrofit().create(NetworkApi.class);
        }
        return mApi;
    }
}
