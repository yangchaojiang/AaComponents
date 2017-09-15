package com.example.aac.data.fragment.list.defaults;

import com.aac.expansion.list.AacListFragmentPresenter;

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestFragmentPresenter extends AacListFragmentPresenter<TestListFragment, String> {

    //private TestDataViewModel viewModel;
    @Override
    protected void onCreateView() {
        super.onCreateView();
      //  viewModel = getViewModel(TestDataViewModel.class);
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     */
    @Override
    protected void lazyLoad() {
      //  viewModel.getListData(1).observe(getView(), getDataSubscriber());
    }

    @Override
    public void setLoadData(int pager) {
      //  viewModel.getListData(pager).observe(getView(), getDataSubscriber());
    }
}
