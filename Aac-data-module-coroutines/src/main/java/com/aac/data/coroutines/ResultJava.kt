package com.aac.data.coroutines

/**
 * author  yangc
 * date 2018/3/15
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class ResultJava<T> {

    var result: T? = null
        private set
    var error: Throwable? = null
        private set

    operator fun component1(): T? {
        return result
    }

    operator fun component2(): Throwable? {
        return error
    }

    companion object {
        fun <T> of(error: Throwable): ResultJava<T> {
            val bean = ResultJava<T>()
            bean.error = error
            return bean
        }

        fun <T> of(bean: T): ResultJava<T> {
            val bean2 = ResultJava<T>()
            bean2.result = bean
            return bean2
        }
    }
}
