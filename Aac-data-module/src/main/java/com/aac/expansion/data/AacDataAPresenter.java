package com.aac.expansion.data;


import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.aac.expansion.ener.PostDataListener;
import com.aac.module.ui.AacPresenter;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public abstract class AacDataAPresenter<V extends AacDataActivity, M> extends AacPresenter<V> implements PostDataListener<M> {
    private Observer<M> observeForever = m -> {
        if (m != null) {
            postData(m);
        } else {
            postError(new Throwable(new NullPointerException()));
        }
    };

    @Override
    public void postData(@NonNull M m) {
        getView().setBaseData(m);
    }

    @Override
    public void postError(Throwable e) {
        getView().setError(new Throwable(new NullPointerException()));
    }

    /**
     * 订阅
     *
     * @return Observer
     ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }

    @Override
    public void retryData() {
        setLoadListData(1);
    }

    /***
     * 列表加载分页数据
     *
     * @param pager 分页 等于1 刷新
     ***/
    public void setLoadListData(int pager) {
    }
}
