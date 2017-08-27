package com.aac.expansion.data;


import android.arch.lifecycle.Observer;

import com.aac.module.ui.AacActivityPresenter;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public class AacDataAPresenter<V extends AacDataActivity, M> extends AacActivityPresenter<V> {
    private Observer<M> observeForever = new Observer<M>() {
        @Override
        public void onChanged(M m) {
            if (m != null) {
                getView().setData(m);
            } else {
                getView().setError(new Throwable(new NullPointerException()));
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observeForever = null;
    }

    /***
     * 手动发布数据
     **/
    public void postData(M data) {
        getView().setData(data);
    }
    /**
     * 订阅
     ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }

}
