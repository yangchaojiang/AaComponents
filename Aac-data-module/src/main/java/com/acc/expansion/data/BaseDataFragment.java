package com.acc.expansion.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acc.module.ui.BaseLifecycleFragment;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment 数据view
 */

public abstract class BaseDataFragment<P extends BaseDataFragmentPresenter, M> extends BaseLifecycleFragment<P> {
    private LoadViewHelper helper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

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

    public LoadViewHelper getHelper() {
        return helper;
    }

    /***
     * 是否加载中布局
     * @param   contentView 内容布局
     * **/
    public void showLoading(View contentView) {
        helper = new LoadViewHelper(contentView);
        helper.showLoading();
    }

    /***
     * 内容布局id
     ***/
    public abstract int getContentLayout();

    /***
     * 返回数据
     ***/
    public abstract void setData(@NonNull M data);

    /***
     * 错误处理
     **/
    public abstract void setError(Throwable e);

}
