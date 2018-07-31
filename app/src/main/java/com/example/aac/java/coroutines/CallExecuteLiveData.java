package com.example.aac.java.coroutines;

import android.arch.lifecycle.MutableLiveData;

import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * author  yangc
 * date 2017/12/19
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public final class CallExecuteLiveData<T> extends MutableLiveData<T> implements Callback<T> {
    private final Call<T> call;
    private boolean terminated = false;

    CallExecuteLiveData(final Call<T> call) {
        this.call = call;

    }

    @Override
    protected void onActive() {
        super.onActive();
        call.execute(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        call.cancel();
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {

    }
    @Override
    public void onSuccess(Response<T> response) {
        if (call.isCanceled()) return;
        try {
            T data=response.body();
            postValue(data);
        } catch (Exception e) {
            if (terminated) {
                RxJavaPlugins.onError(e);
            } else {
                onError(response);
            }
        }
    }

    @Override
    public void onCacheSuccess(Response<T> response) {
        onSuccess(response);
    }

    @Override
    public void onError(Response<T> response) {
        if (call.isCanceled()) return;

        Throwable throwable = response.getException();
        try {
            terminated = true;
            postValue(null);
        } catch (Throwable inner) {
            Exceptions.throwIfFatal(inner);
            RxJavaPlugins.onError(new CompositeException(throwable, inner));
        }
    }

    @Override
    public void onFinish() {
        if (call.isCanceled()) return;
        try {
            terminated = true;
        } catch (Throwable inner) {
            Exceptions.throwIfFatal(inner);
            RxJavaPlugins.onError(inner);
        }
    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }

}
