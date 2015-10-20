package com.byteshaft.order_booker.utils;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.List;


public class NotificationUtils {

    public NotificationUtils() {
    }

    public void showNotificationMessage(String title, String message, Intent intent) {
        if (TextUtils.isEmpty(message))
            return;
            // notification icon
            int icon = R.mipmap.ic_launcher;
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            AppGlobals.getContext(),
                            0,
                            intent,
                            PendingIntent.FLAG_CANCEL_CURRENT
                    );
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    AppGlobals.getContext());
            Notification notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(inboxStyle)
                    .setContentIntent(resultPendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setLargeIcon(BitmapFactory.decodeResource(AppGlobals.getContext().getResources(), icon))
                    .setContentText(message)
                    .build();

            NotificationManager notificationManager = (NotificationManager)
                    AppGlobals.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(AppGlobals.NOTIFICATION_ID, notification);

//            intent.putExtra("title", title);
//            intent.putExtra("message", message);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            AppGlobals.getContext().startActivity(intent);
        }

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }
        return isInBackground;
    }
}
