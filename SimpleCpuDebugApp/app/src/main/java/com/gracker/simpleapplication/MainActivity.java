package com.gracker.simpleapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int THREAD_NUMBER = 10;

    private TextView mTextView = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        for (int i = 0; i < THREAD_NUMBER; i++) {
            createNewThread();
        }
        mContext = this;

        updateComputeTime();
    }

    public void updateComputeTime() {
        mTextView.setText("当前线程数量为 : " + THREAD_NUMBER);
    }

    // 使用 while 循环来一直进行斐波那契数列的计算.
    private void createNewThread() {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    fibonacciRecursive(100);
                    Log.v(TAG, Thread.currentThread().getName() +
                            " mContext =" + mContext.getPackageName());
                }
            }
        });

        newThread.start();
    }

    // 使用递归来计算斐波那契数列
    public int fibonacciRecursive(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

}
