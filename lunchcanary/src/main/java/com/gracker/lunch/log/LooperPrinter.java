package com.gracker.lunch.log;

import android.util.Printer;

import com.gracker.lunch.LogUtils;
import com.gracker.lunch.LunchCanary;

/**
 * Created by gaojianwu on 16-5-12.
 */
public class LooperPrinter implements Printer {

    private boolean mStartedPrinting = false;

    public LooperPrinter() {

    }

    @Override
    public void println(String x) {
        LogUtils.LogD(x);
        if (!mStartedPrinting) {
            mStartedPrinting = true;
            LunchCanary.dumpStack(Thread.currentThread());
        } else {
            mStartedPrinting = false;
        }
    }
}
