package com.aac.expansion.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aac.expansion.ener.ViewGetDataener;
import com.aac.module.ui.AacFragment;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment 数据view
 */

public abstract class AacDataFragment<P extends AacDataFPresenter, M> extends AacFragment<P>  implements ViewGetDataener<M> {
    private LoadViewHelper helper;
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInit = true;
        /**初始化的时候去加载数据**/
        if (setOpenLazyLoad()) isCanLoadData();
    }

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
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (setOpenLazyLoad()) isCanLoadData();
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
     * 获取设置的布局
     *
     * @return View
     */
    protected View getContentView() {
        return getView();
    }
    /**
     * 开启懒加载
     *
     * @return  setOpenLazyLoad true  开启 false
     **/
    protected    boolean  setOpenLazyLoad(){
        return false;
    }
    /***
     * 父类调用方法，用于切换
     **/
    void setBaseData(@NonNull M data) {
        setData(data);
        if (helper != null) {
            helper.showContent();
        }
    }
    /***
     * 父类调用方法，用于切换
     **/
    void setBaseError(Throwable e) {
        setError(e);
        if (helper != null) {
            helper.showError();
        }
    }
    @Override
    public LoadViewHelper getLoadViewHelper() {
        return helper;
    }
    /***
     * 是否加载中布局
     *
     * @param contentView 内容布局
     **/
    public void showLoading(View contentView) {
        helper = new LoadViewHelper(contentView);
        helper.showLoading();
    }


}
