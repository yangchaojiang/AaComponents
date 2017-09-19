package com.example.aac.data.activity;

import com.aac.expansion.list.AacListPresenter;
import com.example.aac.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataListPresenter extends AacListPresenter<TestDataListActivity, String> {
    private TestDataViewModel viewModel;
    @Override
    protected void onCreate() {
        super.onCreate();
        viewModel = getViewModel(TestDataViewModel.class);

    }
    /***
     * 没有进入页面没有开启刷新需要说手动调用
     * **/
    @Override
    public void setLoadData(int pager) {
        viewModel.getListData(pager).observe(getView(), getDataSubscriber());
    }
}
