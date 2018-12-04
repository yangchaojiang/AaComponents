package com.example.aac.utils.rwar.presenter;


import com.aac.module.ui.AacPresenter;
import com.example.aac.utils.rwar.model.RwarViewModel;
import com.example.aac.utils.rwar.ui.RwarActivity;

/**
* Created by yangc on 2018/11/27 17:29:04
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/public class RwarPresenter extends AacPresenter<RwarActivity> {

    public static final String TAG = RwarPresenter.class.getName();
    private RwarViewModel mRwar;
    @Override
    public void onCreate() {
        super.onCreate();
        mRwar = getViewModel(RwarViewModel.class);
    }


}
