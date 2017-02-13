package com.gracker.lunch;

import java.io.File;
import java.io.Serializable;

import static com.gracker.lunch.Preconditions.checkNotNull;


/**
 * Created by gaojianwu on 16-4-25.
 */
public class TraceDump implements Serializable {

    public interface Listener {
        void analyze(TraceDump heapDump);
    }

    /** The heap dump file, which you might want to upload somewhere. */
    public final File heapDumpFile;

    public final String referenceKey;

    /**
     * User defined name to help identify the leaking instance.
     */
    public final String referenceName;

    /** Time from the request to watch the reference until the GC was triggered. */
    public final long watchDurationMs;
    public final long gcDurationMs;
    public final long heapDumpDurationMs;

    public TraceDump(File heapDumpFile, String referenceKey, String referenceName,
                     long watchDurationMs, long gcDurationMs, long heapDumpDurationMs) {
        this.heapDumpFile = checkNotNull(heapDumpFile, "heapDumpFile");
        this.referenceKey = checkNotNull(referenceKey, "referenceKey");
        this.referenceName = checkNotNull(referenceName, "referenceName");
        this.watchDurationMs = watchDurationMs;
        this.gcDurationMs = gcDurationMs;
        this.heapDumpDurationMs = heapDumpDurationMs;
    }
}
