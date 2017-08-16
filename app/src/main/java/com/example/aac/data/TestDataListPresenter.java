package com.example.aac.data;

import com.acc.expansion.list.BaseListPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataListPresenter extends BaseListPresenter<TestDataListActivity, String> {
    private TestDataViewModel viewModel;
    @Override
    protected void onCreate() {
        super.onCreate();
        viewModel = getViewModel(TestDataViewModel.class);
        viewModel.getListData(1).observe(getView(), getDataSubscriber());
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
    }

    @Override
    public void setPageData(int pager) {
        viewModel.getListData(pager).observe(getView(), getDataSubscriber());
    }

    @Override
    public void refresh() {
        viewModel.getListData(1).observe(getView(), getDataSubscriber());
    }
}
