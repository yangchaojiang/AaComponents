package com.aac.expansion.list;

import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aac.module.ui.AacFragmentPresenter;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Fragment 列表数据理处理
 */

public abstract class AacListFragmentPresenter<V extends AacListFragment, M> extends AacFragmentPresenter<V> {

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
    public void postValue(@NonNull List<M> data) {
        getView().setData(data);
    }

    /***
     * 加载分页数据 列表有分页
     *
     * @param pager  等于1刷新
     ***/
    public abstract void setLoadData(int pager);
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     *
     */
    protected abstract void lazyLoad();


    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {

    }
}
