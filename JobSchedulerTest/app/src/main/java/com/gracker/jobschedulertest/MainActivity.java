package com.gracker.jobschedulertest;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int PERIODIC_TIME = 24 * 60 * 60 * 1000;

    JobScheduler jobScheduler;
    ComponentName componentName;
    int mJobId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initJobScheduler();
    }

    public void initJobScheduler() {
        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        componentName = new ComponentName(this, GetDateService.class);
        JobInfo getDataTask = new JobInfo.Builder(mJobId, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .setPeriodic(PERIODIC_TIME)
                .build();
        jobScheduler.schedule(getDataTask);
    }
}
