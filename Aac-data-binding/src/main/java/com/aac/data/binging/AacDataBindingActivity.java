package com.aac.data.binging;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.aac.expansion.ener.ViewGetDataListener;
import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacActivity;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Activity数据父类
 */

@RequiresPresenter(AacDataBindAPresenter.class)
public  abstract class AacDataBindingActivity<P extends AacDataBindAPresenter> extends AacActivity<P> implements ViewGetDataListener{
    private LoadViewHelper helper;
    private ViewDataBinding s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s = DataBindingUtil.setContentView(this, getContentLayoutId());
    }

    /***
     * 获取
     * ***/
    public <T extends ViewDataBinding> T getDataBind() {
        return (T) s;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            helper.onDestroy();
        }
    }

    @Override
    public void setError(Throwable e) {

    }

    @Override
    public void showLoadView() {
        if (helper != null) {
            helper.showLoading();
        }
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
    public void initLoadHelper(@NonNull View view) {
        helper=new LoadViewHelper(view);
        showLoadView();
    }

    /***
         *父类调用方法，用于切换
         * **/
    void setBaseError(Throwable e) {
        if (helper != null) {
            helper.showError();
        }
        setError(e);
    }

    @Override
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }

}
