package com.byteshaft.order_booker;


import android.app.Application;
import android.content.Context;

public class AppGlobals extends Application{

    private static Context sContext;
    public static final String APP_ID = "EieYzwuvJCqr2xFlwy8n7MAuofRj4LHa1F1MCudO";
    public static final String CLIENT_ID = "9sIi4bCDTLYXzd5y0JG5pBaAwaqzJVzlZnfiTvdk";
    public static final int NOTIFICATION_ID = 2112;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_Name = "name";
    public static final String KEY_address = "address";
    public static final String KEY_MOBILE_NUMBER = "number";

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }

    public static String getLogTag(Class aClass) {
        return aClass.getName();
    }
}
