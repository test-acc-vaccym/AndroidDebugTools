package com.gracker.alphaanimationtest;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Gracker", "ClassCount = " + Debug.getLoadedClassCount());
        Log.v("Gracker", "getNativeHeapAllocatedSize = " + Debug.getNativeHeapAllocatedSize());
        Log.v("Gracker", "getNativeHeapFreeSize = " + Debug.getNativeHeapFreeSize());
        Log.v("Gracker", "getNativeHeapSize = " + Debug.getNativeHeapSize());
        Log.v("Gracker", "getPss = " + Debug.getPss());
        Log.v("Gracker", "getBinderDeathObjectCount = " + Debug.getBinderDeathObjectCount());
        Log.v("Gracker", "getBinderLocalObjectCount = " + Debug.getBinderLocalObjectCount());
        Log.v("Gracker", "getBinderProxyObjectCount = " + Debug.getBinderProxyObjectCount());
        Log.v("Gracker", "getBinderReceivedTransactions = " + Debug.getBinderReceivedTransactions());
        Log.v("Gracker", "getBinderSentTransactions = " + Debug.getBinderSentTransactions());
        Log.d("Gracker", Log.getStackTraceString(new Throwable()));
        Log.d("Gracker", "getDrawingTime = " + getWindow().getDecorView().getDrawingTime());
        Log.d("Gracker", "getTransitionName = " + getWindow().getDecorView().getTransitionName());

        mImageView = (ImageView) findViewById(R.id.imageview);

//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
//        alphaAnimation.setDuration(1000);
//        mImageView.startAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });


    }
}
