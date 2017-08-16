package com.example.aac.data;

import android.util.Log;

import com.acc.expansion.data.BaseDataPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TesDataPresenter extends BaseDataPresenter<TestDataActivity, String> {
    public static final String TAG = "TesDataPresenter";
    private TestDataViewModel dataViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        dataViewModel = gettApplicationViewModel(TestDataViewModel.class);
        dataViewModel.getData().observe(getView(), getDataSubscriber());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
