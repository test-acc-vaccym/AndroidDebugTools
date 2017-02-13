package com.gracker.lunch.log;

import android.util.Log;
import android.util.Printer;

/**
 * Created by gaojianwu on 16-5-12.
 */
public class DefaultLooperPrinter implements Printer {
    @Override
    public void println(String x) {
        Log.d("", x);
    }
}
