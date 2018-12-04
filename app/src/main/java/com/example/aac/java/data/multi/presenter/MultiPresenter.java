package com.example.aac.java.data.multi.presenter;

import com.aac.expansion.list.AacListAPresenter;
import com.aac.expansion.list.AacMultiListAPresenter;
import com.example.aac.java.data.multi.model.MultiViewModel;
import com.example.aac.java.data.multi.ui.MultiActivity;
import com.example.aac.java.data.multi.bean.Multi;

/**
* Created by yangc on 2018/10/15 11:08:53
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
public class MultiPresenter extends AacMultiListAPresenter<MultiActivity, Multi> {
    public static final String TAG = MultiPresenter.class.getName();
    private MultiViewModel mMulti;
    @Override
    public void onCreate() {
        super.onCreate();
        mMulti = getViewModel(MultiViewModel.class);
    }

     @Override
      public void setLoadData(int pager) {
        // mMulti.getListData(getView(),"id").observe(getView(),getDataSubscriber());
     }
}
