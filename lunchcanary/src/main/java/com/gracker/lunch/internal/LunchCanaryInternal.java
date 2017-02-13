package com.gracker.lunch.internal;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import com.gracker.lunch.R;

import java.lang.reflect.Method;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.HONEYCOMB;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
import static android.content.pm.PackageManager.DONT_KILL_APP;

/**
 * Created by gaojianwu on 16-4-25.
 */
public class LunchCanaryInternal {

    @TargetApi(HONEYCOMB)
    public static void showNotification(Context context, CharSequence contentTitle,
                                        CharSequence contentText, PendingIntent pendingIntent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification;
        if (SDK_INT < HONEYCOMB) {
            notification = new Notification();
            notification.icon = R.drawable.ic_team;
            notification.when = System.currentTimeMillis();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            try {
                Method method =
                        Notification.class.getMethod("setLatestEventInfo", Context.class, CharSequence.class,
                                CharSequence.class, PendingIntent.class);
                method.invoke(notification, context, contentTitle, contentText, pendingIntent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            Notification.Builder builder = new Notification.Builder(context) //
                    .setSmallIcon(R.drawable.ic_team)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle(contentTitle)
                    .setContentText(contentText)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            if (SDK_INT < JELLY_BEAN) {
                notification = builder.getNotification();
            } else {
                notification = builder.build();
            }
        }
        notificationManager.notify(0xDEAFBEEF, notification);
    }

    public static void setEnabled(Context context, final Class<?> componentClass,
                                  final boolean enabled) {
        final Context appContext = context.getApplicationContext();
    }

    public static void setEnabledBlocking(Context appContext, Class<?> componentClass,
                                          boolean enabled) {
        ComponentName component = new ComponentName(appContext, componentClass);
        PackageManager packageManager = appContext.getPackageManager();
        int newState = enabled ? COMPONENT_ENABLED_STATE_ENABLED : COMPONENT_ENABLED_STATE_DISABLED;
        // Blocks on IPC.
        packageManager.setComponentEnabledSetting(component, newState, DONT_KILL_APP);
    }
}
