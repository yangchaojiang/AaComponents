package com.example.aac.data.fragment.data;


import com.acc.expansion.data.BaseDataFragmentPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataFragmentPresenter extends BaseDataFragmentPresenter<TestDataFragment, String> {
    public static final String TAG = "TestFragmentPresenter";
    private TestDataViewModel viewModel;


    @Override
    protected void onCreateView() {
        super.onCreateView();
        viewModel=getViewModel(TestDataViewModel.class);
        viewModel.getData().observe(getView(),getDataSubscriber());
    }
}
