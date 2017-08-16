package com.acc.expansion.data;


import android.arch.lifecycle.Observer;
import com.acc.module.ui.BaseFragmentLifecycle;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment数据业务
 */

public class BaseDataFragmentPresenter<V extends BaseDataFragment, M> extends BaseFragmentLifecycle<V> {


    private Observer<M> observeForever = new Observer<M>() {
        @Override
        public void onChanged(M m) {
            if (m != null) {
                getView().setBaseData(m);
            } else {
                getView().setBaseError(new Throwable(new NullPointerException()));
            }
        }
    };
    /***
     * 可以子线程数据ui线程手动发布数据
     **/
    public void postData(M data) {
        getView().setBaseData(data);
    }
    /**
     *
     * ***/
    public Observer<M> getDataSubscriber() {
        return observeForever;
    }
}
