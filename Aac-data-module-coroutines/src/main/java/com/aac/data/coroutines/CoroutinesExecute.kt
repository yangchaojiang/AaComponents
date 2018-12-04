package com.aac.data.coroutines

import com.lzy.okgo.adapter.Call
import kotlinx.coroutines.*

class CoroutinesExecute<T>(private val call: Call<T>)//订阅
{

    fun onSubscribe(mSuccess: (mSuccess: T) -> Unit, mError: (mThrowable: Throwable?) -> Unit): Job {
        return GlobalScope.launch {
            val (ok, mError2) = onSubscribe().await()
            if (ok != null) {
                mSuccess(ok)
            } else {
                mError(mError2)
            }
        }

    }

    private fun onSubscribe() = GlobalScope.async(Dispatchers.IO) {
        try {
            if (this.isActive) {
                val mResponse = call.execute()
                if (!call.isCanceled && mResponse.isSuccessful && mResponse.body() != null) {
                    ResultJava.of(mResponse.body())
                } else {
                    ResultJava.of<T>(Throwable(mResponse.message()))
                }
            } else {
                ResultJava.of<T>(Throwable("can"))
            }

        } catch (e: Exception) {
            ResultJava.of<T>(Throwable(e.message))
        }

    }
}


