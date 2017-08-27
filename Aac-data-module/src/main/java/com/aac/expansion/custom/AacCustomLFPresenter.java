package com.aac.expansion.custom;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.aac.module.ui.AacFragmentPresenter;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Fragment 列表数据理处理
 */

public abstract class AacCustomLFPresenter<V extends AacCustomListFragment, M> extends AacFragmentPresenter<V> {

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
     * 加载分页数据 列表有分页
     *
     * @param pager
     ***/
    public void setPageData(int pager) {
    }

    /**
     * 刷新更新数据
     */
    public abstract void refresh();
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
