package com.byteshaft.order_booker.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.byteshaft.order_booker.AppGlobals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public void setValuesOfStrings(String personsName, String address) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppGlobals.KEY_Name, personsName);
        editor.putString(AppGlobals.KEY_address, address);
        editor.commit();
    }

    public String getDataFromSharedPreference(String name) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(name, "");
    }

    public boolean isNetworkAvailable(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public boolean isInternetWorking() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
