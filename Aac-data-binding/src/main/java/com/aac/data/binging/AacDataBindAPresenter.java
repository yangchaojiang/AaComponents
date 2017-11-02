package com.aac.data.binging;


import android.databinding.ViewDataBinding;

import com.aac.module.ui.AacPresenter;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public class AacDataBindAPresenter<V extends AacDataBindingActivity> extends AacPresenter<V> {


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    /***
     * 获取
     * ***/
    public <T extends ViewDataBinding> T getDataBind() {
        return (T) getView().getDataBind();
    }

}
