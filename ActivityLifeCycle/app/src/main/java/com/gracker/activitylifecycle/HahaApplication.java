package com.gracker.activitylifecycle;

import android.app.Application;
import android.util.Log;

/**
 * Created by gaojianwu on 16-5-17.
 */
public class HahaApplication  extends Application{

    private static final String TAG = "Gracker";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"HahaApplication.onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.v(TAG,"HahaApplication.onTerminate");
    }
}
