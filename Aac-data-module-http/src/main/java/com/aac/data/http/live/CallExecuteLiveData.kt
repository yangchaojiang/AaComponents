package com.aac.data.http

import android.arch.lifecycle.MutableLiveData

import com.lzy.okgo.adapter.Call
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request

import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins

/**
 * author  yangc
 * date 2017/12/19
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class CallExecuteLiveData<T> (private val call: Call<T>) : MutableLiveData<T>(), Callback<T> {
    private var terminated = false

    override fun onActive() {
        super.onActive()
        call.execute(this)
    }

    override fun onInactive() {
        super.onInactive()
        call.cancel()
    }

    override fun onStart(request: Request<T, out Request<*, *>>) {

    }

    override fun onSuccess(response: Response<T>) {
        if (call.isCanceled) return
        try {
            val data = response.body()
            postValue(data)
        } catch (e: Exception) {
            if (terminated) {
                RxJavaPlugins.onError(e)
            } else {
                onError(response)
            }
        }

    }

    override fun onCacheSuccess(response: Response<T>) {
        onSuccess(response)
    }

    override fun onError(response: Response<T>) {
        if (call.isCanceled) return

        val throwable = response.exception
        try {
            terminated = true
            postValue(null)
        } catch (inner: Throwable) {
            Exceptions.throwIfFatal(inner)
            RxJavaPlugins.onError(CompositeException(throwable, inner))
        }

    }

    override fun onFinish() {
        if (call.isCanceled) return
        try {
            terminated = true
        } catch (inner: Throwable) {
            Exceptions.throwIfFatal(inner)
            RxJavaPlugins.onError(inner)
        }

    }

    override fun uploadProgress(progress: Progress) {

    }

    override fun downloadProgress(progress: Progress) {

    }

    @Throws(Throwable::class)
    override fun convertResponse(response: okhttp3.Response): T? {
        return null
    }

}
