package com.example.aac.data.activity;

import android.util.Log;

import com.aac.expansion.data.AacDataAPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TesDataPresenter extends AacDataAPresenter<TestDataActivity, String> {
    public static final String TAG = "TesDataBindPresenter";
    private TestDataViewModel dataViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        dataViewModel = getApplicationViewModel(TestDataViewModel.class);
        dataViewModel.getData().observe(getView(), getDataSubscriber());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void  getLoad(){
        dataViewModel.getData().observe(getView(), getDataSubscriber());
    }
}
