@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

@file:JvmName("Utils")
@file:JvmMultifileClass


package com.aac.data.http.utils

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author  yangc
 * date 2018/5/2
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

fun <T> defaultSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer { upstream -> upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()) }
}