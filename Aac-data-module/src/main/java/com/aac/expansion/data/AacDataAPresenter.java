package com.aac.expansion.data;


import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.aac.module.ui.AacPresenter;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public class AacDataAPresenter<V extends AacDataActivity, M> extends AacPresenter<V> {
    private Observer<M> observeForever = m -> {
        if (m != null) {
            getView().setBaseData(m);
        } else {
            getView().setBaseError(new Throwable(new NullPointerException()));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observeForever = null;
    }

    /***
     * 手动发布数据
     * @param  data 数据
     *
     **/
    public void postData(@NonNull M data) {
        getView().setBaseData(data);
    }
    /**
     * 订阅
     * @return   Observer
     ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }

}
