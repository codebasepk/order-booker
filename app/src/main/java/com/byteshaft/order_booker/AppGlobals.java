package com.byteshaft.order_booker;


import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.provider.Settings;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.HashMap;

public class AppGlobals extends Application{

    private static Context sContext;
    public static final String APP_ID = "EieYzwuvJCqr2xFlwy8n7MAuofRj4LHa1F1MCudO";
    public static final String CLIENT_ID = "9sIi4bCDTLYXzd5y0JG5pBaAwaqzJVzlZnfiTvdk";
    public static final String KEY_Name = "name";
    public static final String KEY_address = "address";
    public static final String KEY_MOBILE_NUMBER = "number";
    public static String sAndroid_id;
    private static boolean sSnacksSession = false;
    private static boolean sSuperMarketSession = false;
    private static HashMap<String, String> orderHashMap;
    public static Typeface typeface;
    private static HashMap<String, Integer> quantityHashMap;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
       sAndroid_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Parse.initialize(this, APP_ID, CLIENT_ID);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("user", sAndroid_id.trim());
        installation.saveInBackground();
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/BradBunR.ttf");
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getLogTag(Class aClass) {
        return aClass.getName();
    }

    public static void setSnacksSessionStatus(boolean value) {
        sSnacksSession = value;
    }

    public static boolean getSnacksSessionStatus() {
        return sSnacksSession;
    }

    public static void setSuperMarketSessionStatus(boolean value) {
        sSuperMarketSession = value;
    }

    public static boolean getSuperMarketSessionStatus() {
        return sSuperMarketSession;
    }

    public static void initializeHashMap() {
        orderHashMap =  new HashMap<>();
        quantityHashMap = new HashMap<>();
    }
    public static void addOrderToHashMap(String key, String value) {
        orderHashMap.put(key, value);
    }

    public static void removeOrderFromHashMap(String key) {
        orderHashMap.remove(key);
    }

    public static HashMap<String , String> getFinalOrdersHashMap() {
        return orderHashMap;
    }

    public static void addQuantityToHashMap(String key, int value) {
        quantityHashMap.put(key, value);
    }

    public static void removeQuantityFromHashMap(String key) {
        quantityHashMap.remove(key);
    }

    public static HashMap<String, Integer> getQuantityHashMap() {
        return quantityHashMap;
    }
}
