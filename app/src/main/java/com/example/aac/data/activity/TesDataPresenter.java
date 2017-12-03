package com.example.aac.data.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aac.expansion.data.AacDataAPresenter;
import com.example.aac.model.TestDataViewModel;


/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TesDataPresenter extends AacDataAPresenter<TestDataActivity, String> {
    public static final String TAG = TesDataPresenter.class.getName();
    private TestDataViewModel dataViewModel;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        dataViewModel = getApplicationViewModel(TestDataViewModel.class);
        getLoad();
    }


    @Override
    public void retryData() {
        getLoad();
    }

    public void getLoad() {
        dataViewModel.getData().observe(getView(), getDataSubscriber());
    }
}
