@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

@file:JvmName("Utils")
@file:JvmMultifileClass


package com.aac.data.http.utils

import android.arch.lifecycle.LiveData
import com.aac.data.http.converter.JsonObjectConverter
import com.alibaba.fastjson.JSONObject
import com.keba.utils.LiveDataAdapter
import com.lzy.okgo.OkGo
import com.lzy.okgo.convert.Converter
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.lzy.okrx2.adapter.FlowableResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author  yangc
 * date 2018/5/2
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

/***
 * Rx线程转换
 * ***/
fun <T> defaultSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()) }
}

/***
 * @param  response Response
 * ***/
private fun <E> response(response: Response<E>): Flowable<E> {
    return Flowable.create({
        val body = response.body()
        if (body != null) {
            it.onNext(body)
        } else {
            it.onError(Throwable("null"))
        }
    }, BackpressureStrategy.DROP)
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return  GetRequest<E>
 * ***/
private fun <E> httpGet(url: String, params: HttpParams?, converter: Converter<E>): GetRequest<E> {
    return OkGo.get<E>(url).params(params).converter(converter)
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return  PostRequest<E>
 * ***/
private fun <E> httpPost(url: String, params: HttpParams?, converter: Converter<E>): PostRequest<E> {
    return OkGo.post<E>(url).params(params).converter(converter)
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return  Flowable<E>
 * ***/
fun <E> httpRxGet(url: String, params: HttpParams, converter: Converter<E>): Flowable<E> {
    return httpGet(url, params, converter).adapt(FlowableResponse<E>())
            .flatMap { response(it) }.compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return  Flowable<E>
 * ***/
fun <E> httpRxPost(url: String, params: HttpParams, converter: Converter<E>): Flowable<E> {
    return httpPost(url, params, converter).adapt(FlowableResponse<E>())
            .flatMap { response(it) }.compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param key  参数key
 * @param value  value
 * @param converter  解析器
 * @return  Flowable<E>
 * ***/
fun <E> httpRxget2(url: String, key: String, value: Any ,converter: Converter<E>): Flowable<E> {
    val params = HttpParams()
    params.put(key, value.toString())
    return httpGet(url, params, converter).adapt(FlowableResponse<E>())
            .flatMap { response(it) }.compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param key  参数key
 * @param value  value
 * @param converter  解析器
 * @return  Flowable<E>
 * ***/
fun <E> httpRxPost(url: String, key: String, value: Any, converter: Converter<E>): Flowable<E> {
    val params = HttpParams()
    params.put(key, value.toString())
    return httpPost(url, params, converter).adapt(FlowableResponse<E>())
            .flatMap { response(it) }.compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @return  Flowable<JSONObject>
 * ***/
fun httpRxGetJson(url: String, params: HttpParams): Flowable<JSONObject> {
    return httpRxGet(url, params, JsonObjectConverter()).compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @return  Flowable<JSONObject>
 * ***/
fun httpRxPostJson(url: String, params: HttpParams): Flowable<JSONObject> {
    return httpRxPost(url, params, JsonObjectConverter()).compose(defaultSchedulers())
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return LiveData<E>
 * ***/
fun <E> httpLivePost(url: String, params: HttpParams, converter: Converter<E>): LiveData<E> {
    return httpPost(url, params, converter).adapt(LiveDataAdapter<E>())
}

/***
 * @param  url 请求地址
 * @param params  参数
 * @param converter  解析器
 * @return LiveData<E>
 * ***/
fun <E> httpLiveGet(url: String, params: HttpParams, converter: Converter<E>): LiveData<E> {
    return httpGet(url, params, converter).adapt(LiveDataAdapter<E>())
}
/***
 * @param  url 请求地址
 * @param key  参数key
 * @param value  value
 * @param converter  解析器
 * @return LiveData<E>
 * ***/
fun <E> httpLivePost(url: String,key: String, value: Any, converter: Converter<E>): LiveData<E> {
    val params = HttpParams()
    params.put(key, value.toString())
    return httpPost(url, params, converter).adapt(LiveDataAdapter<E>())
}

/***
 * @param  url 请求地址
 * @param key  参数key
 * @param value  value
 * @param converter  解析器
 * @return LiveData<E>
 * ***/
fun <E> httpLiveGet(url: String, key: String, value: Any, converter: Converter<E>): LiveData<E> {
    val params = HttpParams()
    params.put(key, value.toString())
    return httpGet(url, params, converter).adapt(LiveDataAdapter<E>())
}