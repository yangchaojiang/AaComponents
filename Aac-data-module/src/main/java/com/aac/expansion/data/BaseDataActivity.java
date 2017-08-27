package com.aac.expansion.data;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aac.module.ui.BaseLifecycleActivity;
import com.aac.module.pres.RequiresPresenter;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: Activity数据父类
 */

@RequiresPresenter(BaseDataPresenter.class)
public abstract class BaseDataActivity<P extends BaseDataPresenter, M> extends BaseLifecycleActivity<P> {
    private LoadViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
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

    public  LoadViewHelper getHelper(){
        return helper;
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

    /****
     *是否开启加载中页面
     * **/
    public abstract boolean isLoadingOpen();


}
