package com.aac.data.binging;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
public abstract class AacDataBindingActivity<P extends AacDataBindAPresenter> extends AacActivity<P> implements ViewGetDataListener<Object> {
    private LoadViewHelper helper;
    private ViewDataBinding s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        s = DataBindingUtil.setContentView(this, getContentLayout());

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
    public void setData(@NonNull Object data) {
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
