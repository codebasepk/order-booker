package com.byteshaft.order_booker.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.byteshaft.order_booker.AppGlobals;

public class Helpers {

    public static SharedPreferences getPrefrenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public void setValuesOfStrings(String personsName, String address, String mobileNumber) {
        SharedPreferences sharedPreferences = getPrefrenceManager();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppGlobals.KEY_Name, personsName);
        editor.putString(AppGlobals.KEY_address, address);
        editor.putString(AppGlobals.KEY_MOBILE_NUMBER, mobileNumber);
        editor.apply();
    }

    public String getPersonName(String name) {
        SharedPreferences sharedPreferences = getPrefrenceManager();
        return sharedPreferences.getString(name, "");
    }

    public String getAddress(String address) {
        SharedPreferences sharedPreferences = getPrefrenceManager();
        return sharedPreferences.getString(address, "");
    }

    public String getMobileNumber(String mobileNumebr) {
        SharedPreferences sharedPreferences = getPrefrenceManager();
        return sharedPreferences.getString(mobileNumebr, "");
    }


}
