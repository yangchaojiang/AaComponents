package com.example.aac.java;

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
    protected void onCreateView() {
        super.onCreateView();
        Log.d(TAG,"onCreateView");
    }


}
