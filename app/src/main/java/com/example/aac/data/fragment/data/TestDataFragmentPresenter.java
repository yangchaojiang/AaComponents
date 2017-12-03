package com.example.aac.data.fragment.data;


import android.util.Log;

import com.aac.expansion.data.AacDataFPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataFragmentPresenter extends AacDataFPresenter<TestDataFragment, String> {
    public static final String TAG = "TestFragmentPresenter";
    private TestDataViewModel viewModel;

    @Override
    protected void onCreateView() {
        super.onCreateView();
        viewModel=getViewModel(TestDataViewModel.class);
        Log.d(TAG,"onCreateView");

    }

    @Override
    protected void lazyLoad() {
        Log.d(TAG,"lazyLoad");
       viewModel.getData().observe(getView(),getDataSubscriber());

    }

    @Override
    public void retryData() {
        lazyLoad();
    }
}
