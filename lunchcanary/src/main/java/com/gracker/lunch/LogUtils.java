package com.gracker.lunch;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by gaojianwu on 16-4-25.
 */
public class LogUtils {

    private static final String TAG = "Gracker";
    private static long sStartTime;

    public static void LogD(String log) {
        Log.d(TAG, log);
    }

    public static void LogStart() {
        sStartTime = SystemClock.elapsedRealtime();
    }

    public static long reportAppLunchTime() {
        return SystemClock.elapsedRealtime() - sStartTime;
    }
}
