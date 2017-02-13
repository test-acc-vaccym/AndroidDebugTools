package com.gracker.meidascannertest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MeidaScannerUtils.scanFile(this, Environment.DIRECTORY_DCIM);
        MeidaScannerUtils.mediaConnectionTest(this);
    }
}
