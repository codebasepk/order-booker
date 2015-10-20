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

/**
 * Created by s9iper1 on 10/20/15.
 */
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
        try {
            boolean isBackground = json.getBoolean("is_background");
            JSONObject data = json.getJSONObject("data");
            String title = data.getString("title");
            String message = data.getString("message");
            if (!isBackground) {
                Intent resultIntent = new Intent(context, MainActivity.class);
                showNotificationMessage(title, message, resultIntent);
            }
        } catch (JSONException e) {
            Log.e(AppGlobals.getLogTag(getClass()), "Push message json exception: " + e.getMessage());
        }
    }

    private void showNotificationMessage(String title, String message, Intent intent) {
        NotificationUtils notificationUtils = new NotificationUtils();
        intent.putExtras(parseIntent.getExtras());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, intent);
    }
}
