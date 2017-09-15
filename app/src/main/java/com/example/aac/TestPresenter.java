package com.example.aac;

import android.util.Log;

import com.aac.module.ui.AacPresenter;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestPresenter extends AacPresenter<MainActivity> {
    public static final String TAG = "TestPresenter";

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
