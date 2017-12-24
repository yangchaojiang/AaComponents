package com.example.aac.java.data.activity;

import android.util.Log;

import com.aac.expansion.data.AacDataAPresenter;
import com.example.aac.java.data.activity.bean.UserBean;
import com.example.aac.java.model.TestDataViewModel;


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
        getData();
    }


    @Override
    public void retryData() {
        getData();
    }

    public void getData() {
       dataViewModel.getData().observe(getView(), getDataSubscriber());
    }
}
