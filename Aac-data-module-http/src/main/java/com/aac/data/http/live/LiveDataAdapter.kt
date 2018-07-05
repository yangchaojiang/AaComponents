package com.keba.utils


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.aac.data.http.CallExecuteLiveData
import com.lzy.okgo.adapter.AdapterParam
import com.lzy.okgo.adapter.Call
import com.lzy.okgo.adapter.CallAdapter

/**
 * author  yangc
 * date 2017/12/17
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 *   try {
val response = call.execute()
if (!call.isCanceled) {
it.onNext(response.body())
}
if (!call.isCanceled) {
it.onComplete()
}
} catch (e: Throwable) {
it.onError(e)
}
 */


   class LiveDataAdapter<T> : CallAdapter<T, MutableLiveData<T>> {
    override fun adapt(call: Call<T>, param: AdapterParam?): MutableLiveData<T>? {
        return CallExecuteLiveData<T>(call)
    }

}
