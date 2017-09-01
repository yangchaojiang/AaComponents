package com.example.aac.data.activity;

import com.aac.expansion.list.AacListActivityPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataListPresenter extends AacListActivityPresenter<TestDataListActivity, String> {
    private TestDataViewModel viewModel;
    @Override
    protected void onCreate() {
        super.onCreate();
        viewModel = getViewModel(TestDataViewModel.class);
        viewModel.getListData(1).observe(getView(), getDataSubscriber());
    }

    @Override
    public void setLoadData(int pager) {
        viewModel.getListData(pager).observe(getView(), getDataSubscriber());
    }
}
