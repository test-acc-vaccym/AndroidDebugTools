package com.gracker.lunch;

import android.app.PendingIntent;

import com.gracker.lunch.internal.DisplayLunchTimeActivity;
import com.gracker.lunch.internal.LunchCanaryInternal;

/**
 * Created by gaojianwu on 16-4-25.
 */


public class DisplayLunchService extends AbstractAnalysisResultService {
    private static final String contentTitle = "LunchTime";
    private static final String contentText = "LunchTime";

    @Override
    protected void onTraceAnalyzed(TraceDump heapDump, AnalysisResult result) {

        PendingIntent pendingIntent;
        pendingIntent = DisplayLunchTimeActivity.createPendingIntent(this, result.className);
        LunchCanaryInternal.showNotification(this, contentTitle, contentText, pendingIntent);
    }
}
