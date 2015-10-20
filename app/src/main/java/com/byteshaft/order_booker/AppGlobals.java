package com.byteshaft.order_booker;


import android.app.Application;
import android.content.Context;
import android.provider.Settings;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class AppGlobals extends Application{

    private static Context sContext;
    public static final String APP_ID = "EieYzwuvJCqr2xFlwy8n7MAuofRj4LHa1F1MCudO";
    public static final String CLIENT_ID = "9sIi4bCDTLYXzd5y0JG5pBaAwaqzJVzlZnfiTvdk";
    public static final int NOTIFICATION_ID = 2112;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_Name = "name";
    public static final String KEY_address = "address";
    public static final String KEY_MOBILE_NUMBER = "number";
    public static final String KEY_ORDER_NAME = "orderThingName";
    public static final String KEY_FROM_WHERE = "fromWhere";
    public static final String KEY_ORDER_TIME_DATE = "orderTimeDate";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Parse.initialize(this, APP_ID, CLIENT_ID);
        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("user", android_id);
        installation.saveInBackground();
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getLogTag(Class aClass) {
        return aClass.getName();
    }
}
