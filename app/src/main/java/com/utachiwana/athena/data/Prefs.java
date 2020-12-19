package com.utachiwana.athena.data;

import android.content.SharedPreferences;

public class Prefs {

    static SharedPreferences pref;

    public static void newInstance(SharedPreferences prefs) {
        pref = prefs;
    }

    public static void setToken(String token) {
        pref.edit().putString("token", token).commit();
    }

    public static String getToken() {
        return pref.getString("token", "");
    }
}
