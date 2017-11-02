package com.example.aac.data.bing;

import android.util.Log;

import com.aac.data.binging.AacDataBindAPresenter;
import com.example.aac.databinding.TestDataViewBinding;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TesDataBindPresenter extends AacDataBindAPresenter<TestDataBindActivity> {
    public static final String TAG = "TesDataBindPresenter";
    private DataBindViewModel dataViewModel;
    private   TestDataViewBinding testDataViewBinding;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
           dataViewModel = getApplicationViewModel(DataBindViewModel.class);
          testDataViewBinding=getDataBind();
        dataViewModel.getBindData(testDataViewBinding);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
