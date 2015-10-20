package com.byteshaft.order_booker.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.byteshaft.order_booker.AppGlobals;

public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public void setValuesOfStrings(String personsName, String address, String mobileNumber) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppGlobals.KEY_Name, personsName);
        editor.putString(AppGlobals.KEY_address, address);
        editor.putString(AppGlobals.KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
    }

    public String getDataFromSharedPreference(String name) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(name, "");
    }
}
