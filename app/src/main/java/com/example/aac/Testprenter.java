package com.example.aac;

import android.util.Log;

import com.aac.module.ui.AacActivityPresenter;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class Testprenter extends AacActivityPresenter<MainActivity> {
    public static final String TAG = "Testprenter";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        Log.d(TAG,"onCreateView");
    }
}
