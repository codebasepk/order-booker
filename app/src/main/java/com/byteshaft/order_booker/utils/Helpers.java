package com.byteshaft.order_booker.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.byteshaft.order_booker.AppGlobals;

public class Helpers {

    public static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public void setValuesOfStrings(String personsName, String address, String mobileNumber,String orderThingName,
                                   String fromWhere,String orderTimeDate) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppGlobals.KEY_Name, personsName);
        editor.putString(AppGlobals.KEY_address, address);
        editor.putString(AppGlobals.KEY_MOBILE_NUMBER, mobileNumber);
        editor.putString(AppGlobals.KEY_ORDER_NAME, orderThingName);
        editor.putString(AppGlobals.KEY_FROM_WHERE, fromWhere);
        editor.putString(AppGlobals.KEY_ORDER_TIME_DATE, orderTimeDate);
        editor.apply();
    }

    public String getPersonName(String name) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(name, "");
    }

    public String getAddress(String address) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(address, "");
    }

    public String getMobileNumber(String mobileNumber) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(mobileNumber, "");
    }

    public String getOrderThingName(String orderThingName) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(orderThingName, "");
    }

    public String getOrderWhereFrom(String fromWhere) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(fromWhere, "");
    }

    public String getOrderTimeDate(String orderTimeDate) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(orderTimeDate, "");
    }
}
