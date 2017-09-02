package com.aac.expansion.custom;


import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.aac.module.ui.AacPresenter;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  自定义数据业务
 */

public abstract class AacCustomALPresenter<V extends AacCustomListActivity, M> extends AacPresenter<V> {

    private Observer<List<M>> observeForever = new Observer<List<M>>() {

        @Override
        public void onChanged(@Nullable List<M> ms) {
            if (ms != null) {
                getView().setData(ms);
            } else {
                getView().setError(new Throwable(new NullPointerException()));
            }
        }
    };


    /**
     * 订阅
     ***/
    public Observer<List<M>> getDataSubscriber() {
        return observeForever;
    }

    /***
     * 手动发布数据
     **/
    public void postValue(List<M> data) {
        getView().setData(data);
    }
    /***
     * 加载分页数据
     *
     * @param pager 分页  等于1
     ***/
    public abstract void setLoadData(int pager);

}
