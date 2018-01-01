package com.aac.expansion.data;


import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

import com.aac.expansion.ener.PostDataListener;
import com.aac.module.ui.AacFragmentPresenter;

/**
 * author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment数据业务
 */

public  abstract class AacDataFPresenter<V extends AacDataFragment, M> extends AacFragmentPresenter<V> implements PostDataListener<M> {

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
        getView().setError(e);

    }

    @Override
    public void retryData() {

    }
    /**
     * 返回订阅数据
     *
     * @return Observer
     ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
    /***
     * 列表加载分页数据
     *
     * @param pager 分页 等于1 刷新
     ***/
    public   void setLoadData(int pager){}

}
