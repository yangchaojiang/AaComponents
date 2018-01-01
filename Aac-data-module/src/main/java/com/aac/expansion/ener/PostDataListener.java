package com.aac.expansion.ener;

import android.support.annotation.NonNull;


/**
 * author  yangc
 * date 2017/12/28
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public interface PostDataListener<M> {

    /***
     * 手动发布数据
     * @param  m 数据
     *
     **/
    void postData(@NonNull M m);
    /**
     * 手动发布错误
     *
     * @param e e
     */
    void postError(Throwable e);
    /**
     * 点击重试加载
     **/
    void  retryData();


}
