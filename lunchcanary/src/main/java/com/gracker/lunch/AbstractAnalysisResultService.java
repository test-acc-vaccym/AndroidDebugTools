package com.gracker.lunch;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public abstract class AbstractAnalysisResultService extends IntentService {
    private static final String HEAP_DUMP_EXTRA = "heap_dump_extra";
    private static final String RESULT_EXTRA = "result_extra";

    public static void sendResultToListener(Context context, String listenerServiceClassName, TraceDump heapDump, AnalysisResult result) {
        Class<?> listenerServiceClass;
        try {
            listenerServiceClass = Class.forName(listenerServiceClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Intent intent = new Intent(context, listenerServiceClass);
        intent.putExtra(HEAP_DUMP_EXTRA, heapDump);
        intent.putExtra(RESULT_EXTRA, result);
        context.startService(intent);
    }

    public AbstractAnalysisResultService() {
        super(AbstractAnalysisResultService.class.getName());
    }

    @Override
    protected final void onHandleIntent(Intent intent) {
        TraceDump heapDump = (TraceDump) intent.getSerializableExtra(HEAP_DUMP_EXTRA);
        AnalysisResult result = (AnalysisResult) intent.getSerializableExtra(RESULT_EXTRA);
        try {
            onTraceAnalyzed(heapDump, result);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            heapDump.heapDumpFile.delete();
        }
    }

    /**
     * Called after a heap dump is analyzed, whether or not a leak was found.
     * Check {@link AnalysisResult#leakFound} and {@link AnalysisResult#excludedLeak} to see if there
     * was a leak and if it can be ignored.
     * <p/>
     * This will be called from a background intent service thread.
     * <p/>
     * It's OK to block here and wait for the heap dump to be uploaded.
     * <p/>
     * The heap dump file will be deleted immediately after this callback returns.
     */
    protected abstract void onTraceAnalyzed(TraceDump heapDump, AnalysisResult result);
}
