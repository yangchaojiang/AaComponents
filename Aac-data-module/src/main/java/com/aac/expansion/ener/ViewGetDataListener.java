package com.aac.expansion.ener;

import android.support.annotation.NonNull;
import android.view.View;

import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/9/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据view 接口
 */


public interface ViewGetDataListener {

    LoadViewHelper getViewLoadHelper();

    /***
     * 错误处理
     *
     * @param e 错误
     **/
    void setError(Throwable e);

    /***
     * 显示加载中布局
     * @param contentView 内容布局
     * ***/
    void initLoadHelper(@NonNull View contentView);

    /***
     * 显示加载中布局
     * ***/
    void showLoadView();

    /***
     * 显示错误布局
     * ***/
    void showErrorView();

    /***
     * 显示内容布局
     * ***/
    void showContentView();
}
