package com.aac.expansion.list;


import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import com.aac.module.ui.AacPresenter;
import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public abstract class AacListPresenter<V extends AacListActivity, M> extends AacPresenter<V> {

    private Observer<List<M>> observeForever = ms -> {
        if (ms != null) {
            getView().setData(ms);
        } else {
            getView().setError(new Throwable(new NullPointerException()));
        }
    };

    /**
     * 订阅
     * @return Observer
     ***/
    public Observer<List<M>> getDataSubscriber() {
        return observeForever;
    }

    /***
     * 手动发布数据
     * @param  data 数据
     **/
    public void postValue(@NonNull List<M> data) {
        getView().setData(data);
    }

    /***
     * 加载分页数据
     *
     * @param pager 分页 等于1 刷新
     ***/
    public abstract void setLoadData(int pager);

}
