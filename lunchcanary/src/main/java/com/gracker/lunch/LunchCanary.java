package com.gracker.lunch;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.os.MessageQueue;
import android.widget.Toast;

import com.gracker.lunch.internal.DisplayLunchTimeActivity;
import com.gracker.lunch.internal.LunchCanaryInternal;
import com.gracker.lunch.log.DefaultLooperPrinter;
import com.gracker.lunch.log.LooperPrinter;

/**
 * Created by gaojianwu on 16-4-25.
 */
public class LunchCanary {
    private static String TAG = "LunchCanary";
    private static final String DEFAULT_TRACE_PATH_PREFIX =
            Environment.getExternalStorageDirectory().getPath() + "/";
    private static final String DEFAULT_TRACE_BODY = "dmtrace";
    private static final String DEFAULT_TRACE_EXTENSION = ".trace";
    private static final String DEFAULT_TRACE_FILE_PATH =
            DEFAULT_TRACE_PATH_PREFIX + DEFAULT_TRACE_BODY
                    + DEFAULT_TRACE_EXTENSION;

    public static void install(Application application) {
        install(application, DisplayLunchService.class);

    }

    public static void install(Application application, Class<? extends AbstractAnalysisResultService> listenerServiceClass) {
//        Debug.startMethodTracingSampling(DEFAULT_TRACE_FILE_PATH, 0, 1000);
        LogUtils.LogStart();
        if (null != application) {
            Looper.myQueue().addIdleHandler(new LunchIdleHandler(application));
//            Looper.getMainLooper().setMessageLogging(new LooperPrinter());
//            //register lifecycle callback
//            application.registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBack());
        }
//        TraceDump.Listener heapDumpListener =
//                new ServiceLunchTimeListener(application, listenerServiceClass);

    }

    public static void dumpStack(Thread thread) {
        StringBuilder stringBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        // Fetch thread stack info
        for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString())
                    .append(lineSeparator);
        }
        LogUtils.LogD("Stack  = " + stringBuilder.toString());
    }

    static class LunchIdleHandler implements MessageQueue.IdleHandler {

        private Application mApplication;

        public LunchIdleHandler(Application application) {
            mApplication = application;
        }

        @Override
        public boolean queueIdle() {
            long appLunchTime = LogUtils.reportAppLunchTime();
            LogUtils.LogD("App lunch time = " + appLunchTime);

//            Debug.stopMethodTracing();

//            Looper.getMainLooper().setMessageLogging(new DefaultLooperPrinter());
            Toast.makeText(mApplication, "LunchTime is " + appLunchTime, Toast.LENGTH_SHORT).show();

//            PendingIntent pendingIntent;
//            pendingIntent = DisplayLunchTimeActivity.createPendingIntent(mApplication, String.valueOf(appLunchTime));
//            LunchCanaryInternal.showNotification(mApplication, "LunchTime", String.valueOf(appLunchTime), pendingIntent);
            return false;
        }
    }

    static class ActivityLifeCycleCallBack implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
