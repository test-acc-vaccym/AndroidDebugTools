package com.gracker.lunch.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gracker.lunch.R;

public class DisplayLunchTimeActivity extends AppCompatActivity {

    private static final String SHOW_LEAK_EXTRA = "show_app_lunchtime";
    private TextView mTextView;

    String visibleLeakRefKey;

    public static PendingIntent createPendingIntent(Context context) {
        return createPendingIntent(context, null);
    }

    public static PendingIntent createPendingIntent(Context context, String referenceKey) {
        Intent intent = new Intent(context, DisplayLunchTimeActivity.class);
        intent.putExtra(SHOW_LEAK_EXTRA, referenceKey);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lunch_time);

        mTextView = (TextView) findViewById(R.id.app_lunch_time);

        Intent intent = getIntent();
        if (intent.hasExtra(SHOW_LEAK_EXTRA)) {
            visibleLeakRefKey = intent.getStringExtra(SHOW_LEAK_EXTRA);
        }

        mTextView.setText(visibleLeakRefKey);
    }
}
