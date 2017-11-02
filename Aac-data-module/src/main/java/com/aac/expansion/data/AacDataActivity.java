package com.aac.expansion.data;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aac.expansion.ener.ViewGetDataListener;
import com.aac.module.ui.AacActivity;
import com.aac.module.pres.RequiresPresenter;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 *
 * @author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Activity数据父类
 */

@RequiresPresenter(AacDataAPresenter.class)
public abstract class AacDataActivity<P extends AacDataAPresenter, M> extends AacActivity<P> implements ViewGetDataListener<M> {
    public LoadViewHelper helper;
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.onDestroy();
        }
    }
    /***
     *父类调用方法，用于切换
     * **/
     void setBaseData(@NonNull M data) {
        setData(data);
        if (helper != null) {
            helper.showContent();
        }
    }
    /***
     *父类调用方法，用于切换
     * **/
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

}
