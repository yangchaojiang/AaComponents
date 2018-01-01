package com.aac.module.rx2.presenter.data;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.aac.expansion.data.AacDataAPresenter;
import com.aac.expansion.data.AacDataActivity;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  数据业务
 */

public abstract   class AacRxDataAPresenter<V extends AacDataActivity, M> extends AacDataAPresenter<V,M> {
    //View的订阅关系，View被销毁时自动取消订阅。
    private Subscription mSubscription;
    private CompositeDisposable compositeDisposable;
    //用于缓存数据的Subscriber
    private BehaviorSubject<M> mData = BehaviorSubject.create();
    private   Subscriber subscriber = new Subscriber<M>() {
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


    /***
     * 手动发布数据
     * @param  data 数据
     *
     **/
    protected void postRxData(@NonNull M data) {
        mData.onNext(data);
    }

    /**
     * 手动发布错误
     *
     * @param e e
     */
    public void postRxError(Throwable e) {
        mData.onError(e);
    }

    /**
     * 订阅
     *
     * @return Subscriber
     ***/
    public Subscriber getDataRxSubscriber() {
        return subscriber;
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

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        //Activity销毁时，取消网络请求
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
        if (mSubscription != null) {
            mSubscription.cancel();
        }
        super.onDestroy();
    }

}
