package com.gracker.alphaanimationtest;

import android.app.Application;

import com.gracker.lunch.LunchCanary;

/**
 * Created by gaojianwu on 16-4-25.
 */
public class SampleApplication extends Application {


    @Override
    public void onCreate() {
        LunchCanary.install(this);
        super.onCreate();
    }
}
