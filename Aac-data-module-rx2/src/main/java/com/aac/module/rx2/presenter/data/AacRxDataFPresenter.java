package com.aac.module.rx2.presenter.data;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.aac.expansion.data.AacDataFPresenter;
import com.aac.expansion.data.AacDataFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  Fragment数据业务
 */

public abstract class AacRxDataFPresenter<V extends AacDataFragment, M> extends AacDataFPresenter<V,M> {

    //View的订阅关系，View被销毁时自动取消订阅。
    private Subscription mSubscription;
    //订阅类管理类
    private CompositeDisposable compositeDisposable;
    //用于缓存数据的Subscriber
    private BehaviorSubject<M> mData = BehaviorSubject.create();
    private Subscriber<M> subscriber = new Subscriber<M>() {
        @Override
        public void onSubscribe(Subscription s) {
            mSubscription = s;
        }

        @Override
        public void onNext(M m) {
            mData.onNext(m);
        }

        @Override
        public void onError(Throwable t) {
            mData.onError(t);
        }

        @Override
        public void onComplete() {

        }
    };

    @CallSuper
    @Override
    protected void onCreate() {
        super.onCreate();
        addDisposable(mData.subscribe(this::postData, this::postError));

    }
    /**
     * 获取缓存数据的Subscriber
     * 可以通过 `getDataSubject().getValue();` 获取缓存数据。
     *
     * @return BehaviorSubject
     */
    public BehaviorSubject<M> getDataSubject() {
        return mData;
    }
    /***
     * 手动发布数据
     * @param  data 数据
     *
     **/
    protected void postRxData(@NonNull M data) {
        mData.onNext(data);
    }

    /**
     * 订阅
     *
     * @return Subscriber
     ***/
    public Subscriber<M> getDataRxSubscriber() {
        return subscriber;
    }


    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    @CallSuper
    @Override
    protected void onDestroy() {
        if (mSubscription != null) {
            mSubscription.cancel();
        }
        //Activity销毁时，取消网络请求
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
        super.onDestroy();
    }

}
