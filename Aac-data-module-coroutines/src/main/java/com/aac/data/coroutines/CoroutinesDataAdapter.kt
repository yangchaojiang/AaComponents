package com.aac.data.coroutines

import com.lzy.okgo.adapter.AdapterParam
import com.lzy.okgo.adapter.Call
import com.lzy.okgo.adapter.CallAdapter

/**
 * author  yangc
 * date 2017/12/17
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class CoroutinesDataAdapter<T> : CallAdapter<T, CoroutinesExecute<T>> {
    override fun adapt(call: Call<T>, param: AdapterParam?): CoroutinesExecute<T>?{
        return CoroutinesExecute(call)
    }

}
