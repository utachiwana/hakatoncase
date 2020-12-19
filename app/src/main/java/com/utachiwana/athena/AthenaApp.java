package com.utachiwana.athena;

import android.app.Application;

import com.utachiwana.athena.data.Prefs;

public class AthenaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.newInstance(getSharedPreferences("settings", MODE_PRIVATE));
    }
}
