package com.byteshaft.order_booker.receivers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.activites.MainActivity;
import com.byteshaft.order_booker.utils.NotificationUtils;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;


public class CustomPushParseReceiver extends ParsePushBroadcastReceiver {

    private Intent parseIntent;
    public CustomPushParseReceiver() {
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        if (intent == null)
            return;
        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            Log.e(AppGlobals.getLogTag(getClass()), "Push received: " + json);
            parseIntent = intent;
            parsePushJson(context, json);

        } catch (JSONException e) {
            Log.e(AppGlobals.getLogTag(getClass()), "Push message json exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    private void parsePushJson(Context context, JSONObject json) {
        String title = null;
        String message = null;
        try {
            title = json.getString("title");
            message = json.getString("response");
            System.out.println(title);
            System.out.println(message);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        Intent resultIntent = new Intent(context, MainActivity.class);
                showNotificationMessage(title, message, resultIntent);
    }

    private void showNotificationMessage(String title, String message, Intent intent) {
        NotificationUtils notificationUtils = new NotificationUtils();
        intent.putExtras(parseIntent.getExtras());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, intent);
    }
}
