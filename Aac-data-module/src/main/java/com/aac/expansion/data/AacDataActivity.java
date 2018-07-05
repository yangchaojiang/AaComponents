package com.aac.expansion.data;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.View;
import com.aac.expansion.ener.ViewGetDataListener;
import com.aac.module.ui.AacActivity;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Activity数据父类
 */

public abstract class AacDataActivity<P extends AacDataAPresenter, M> extends AacActivity<P> implements ViewGetDataListener {
    private LoadViewHelper helper;

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
         if (helper != null) {
             helper.showContent();
         }
         setData(data);
    }


    /***
     *父类调用方法，用于切换
     * **/
    @Override
     public void setError(Throwable e) {
        if (helper != null) {
            helper.showError();
        }
    }

    @Override
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }

    @Override
    public void showLoadView() {
        if (helper != null) {
            helper.showLoading();
        }
    }

    @Override
    public void initLoadHelper(@NonNull View view) {
        helper = new LoadViewHelper(view);
        helper.showLoading();
        helper.setListener(() -> getPresenter().retryData());
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
    /***
     * 数据返回
     * @param data data
     * **/
    public abstract void setData(@NonNull M data);

}
