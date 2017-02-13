package com.gracker.lunch;

import android.content.Context;

/**
 * Created by gaojianwu on 16-4-25.
 */
public class ServiceLunchTimeListener implements TraceDump.Listener{

    private final Context context;
    private final Class<? extends AbstractAnalysisResultService> listenerServiceClass;

    public ServiceLunchTimeListener(Context context,
                                   Class<? extends AbstractAnalysisResultService> listenerServiceClass) {
        this.listenerServiceClass = Preconditions.checkNotNull(listenerServiceClass, "listenerServiceClass");
        this.context = Preconditions.checkNotNull(context, "context").getApplicationContext();
    }

    @Override
    public void analyze(TraceDump heapDump) {

    }
}
