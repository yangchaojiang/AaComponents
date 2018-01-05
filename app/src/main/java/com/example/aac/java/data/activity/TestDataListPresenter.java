package com.example.aac.java.data.activity;


import com.aac.module.rx2.presenter.list.AacRxListAPresenter;
import com.example.aac.java.model.TestDataViewModel;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataListPresenter extends AacRxListAPresenter<TestDataListActivity, String> {
    private TestDataViewModel viewModel;
    @Override
    public void onCreate() {
        super.onCreate();
        viewModel = getViewModel(TestDataViewModel.class);
        setLoadListData(1);
    }

    /***
     * 没有进入页面没有开启刷新需要说手动调用
     * **/
    @Override
    public void setLoadListData(int pager) {
        viewModel.getListData(pager).observe(getView(), getDataSubscriber());
      //  viewModel.getListData(getView(),"id",pager).observe(getView(),getDataSubscriber());
    }
}
