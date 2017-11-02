package com.aac.expansion.ener;

import android.support.annotation.NonNull;

import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/9/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据view 接口
 */


public interface ViewGetDataListener<M> {
    
    LoadViewHelper getLoadViewHelper();

    /***
     * 内容布局id
     * @return int
     ***/
    int getContentLayout();

    /***
     * 返回数据
     *
     * @param data 数据
     ***/
    void setData(@NonNull M data);

    /***
     * 错误处理
     *
     * @param e 错误
     **/
    void setError(Throwable e);

}
