package com.aac.expansion.data;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.aac.expansion.ener.ViewGetDataListener;
import com.aac.module.ui.AacFragment;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment 数据view
 */

public abstract class AacDataFragment<P extends AacDataFPresenter, M> extends AacFragment<P> implements ViewGetDataListener {
    private LoadViewHelper helper;
    protected boolean isInit = false;
    protected boolean isLoad = false;

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInit = true;
        //初始化的时候去加载数据
        if (setOpenLazyLoad()) {
            isCanLoadData();
        }
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.onDestroy();
        }
        isInit = false;
        isLoad = false;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @CallSuper
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (setOpenLazyLoad()) {
            isCanLoadData();
        }
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            getPresenter().lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                getPresenter().stopLoad();
            }
        }
    }


    /**
     * 开启懒加载
     *
     * @return setOpenLazyLoad true  开启 false
     **/
    protected boolean setOpenLazyLoad() {
        return false;
    }

    /***
     * 父类调用方法，用于切换
     **/
    void setBaseData(@NonNull M data) {
        showContentView();
        setData(data);
    }

    /***
     * 父类调用方法，用于切换
     **/
    void setBaseError(Throwable e) {
        showErrorView();
        setError(e);
    }

    @Override
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }

    @Override
    public void initLoadHelper(@NonNull View view) {
        helper = new LoadViewHelper(view);
        helper.showLoading();
        helper.setListener(() -> {
            getViewLoadHelper().showLoading();
            getPresenter().retryData();
        });
    }

    @Override
    public void showErrorView() {
        if (helper != null) {
            helper.showError();
        }
    }

    @Override
    public void showContentView() {
        if (helper != null) {
            helper.showContent();
        }
    }

    @Override
    public void showLoadView() {
        if (helper != null) {
            helper.showLoading();
        }
    }

    /***
     * 数据返回
     * @param data data
     * **/
    public abstract void setData(@NonNull M data);
}
