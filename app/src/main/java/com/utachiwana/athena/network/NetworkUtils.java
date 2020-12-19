package com.utachiwana.athena.network;

import android.util.Log;

import com.utachiwana.athena.BuildConfig;
import com.utachiwana.athena.data.Prefs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static String USER_ID = "X-AUTH-TOKEN";

    private static OkHttpClient mClient;
    private static Retrofit mRetrofit;
    private static NetworkApi mApi;

    public static OkHttpClient getClient() {
        if (mClient == null) {
            mClient = new OkHttpClient();
            mClient.newBuilder().addInterceptor(chain -> {
                Log.d("_______", "intercept: ");
                Request request = chain.request().newBuilder().addHeader(USER_ID, Prefs.getToken()).build();
                return chain.proceed(request);
            }).build();
        }
        return mClient;
    }

    public static void setClient(){
        mClient = null;
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
