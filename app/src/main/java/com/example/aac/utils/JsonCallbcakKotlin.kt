package com.keba.utils


import android.support.annotation.AnyThread
import android.support.annotation.NonNull
import com.alibaba.fastjson.JSONObject
import com.lzy.okgo.callback.AbsCallback
import com.yutils.JsonManager
import java.lang.reflect.Type
import okhttp3.Response

/**
 * Created by yangc on 2017/10/7.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

abstract class JsonCallbcakKotlin<T> : AbsCallback<T> {
    private var type: Type? = null
    private var clazz: Class<T>? = null
    private var key: String? = null

    constructor(@NonNull key: String, clazz: Class<T>) {
        this.clazz = clazz
        this.key = key
    }

    constructor(@NonNull key: String, type: Type) {
        this.type = type
        this.key = key
    }

    override fun onStart(request: com.lzy.okgo.request.base.Request<T, out com.lzy.okgo.request.base.Request<*, *>>?) {
        super.onStart(request)
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        val body = response.body() ?: return null
        val s = JsonManager.parseJsonObject(body.string())
        return if (s.getBoolean("success")!!) {
            when {
                clazz != null -> JsonManager.jsonToBean(s[key].toString(), clazz)
                type != null -> JsonManager.jsonToBean(s[key].toString(), type)
                else -> JsonManager.jsonToBean(body.bytes())
            }
        } else {
            throw IllegalStateException(response.message())
        }
    }

    companion object {
        /*****
         * @param response 請求對象
         * @param type         返回实例对象类型
         * @param key     需要处理对象key
         * @return  T
         * set   TypeReference typeReference = new TypeReference<JsonBean>() {};
        </T> */
        @AnyThread
        @Throws(Exception::class)
        fun <T> getBean(response: Response, type: Type, key: String): T? {
            val body = response.body() ?: return null
            val s = JsonManager.parseJsonObject(body.string())
            return if (s.getBoolean("success")!!) {
                JsonManager.jsonToBean(s?.get(key).toString(), type)
            } else if (!s.getBoolean("success")!!) {
                JsonManager.jsonToBean(body.bytes())
            } else {
                throw IllegalStateException(response.message())
            }
        }

        /*****
         * @param response 請求對象
         * @param clazz         返回实例对象类型
         * @param key     需要处理对象key
         * @return  T
        </T> */
        @AnyThread
        @Throws(Exception::class)
        fun <T> getBean(response: Response, clazz: Class<T>, key: String): T? {
            val body = response.body() ?: return null
            val s = JsonManager.parseJsonObject(body.string())
            return if (s.getBoolean("success")!!) {
                JsonManager.jsonToBean(s?.get(key).toString(), clazz)
            } else if (!s.getBoolean("success")!!) {
                JsonManager.jsonToBean(body.bytes())
            } else {
                throw IllegalStateException(response.message())
            }
        }

        /*****
         * @param response 請求對象
         * @param clazz         返回实例对象类型
         * @param key     需要处理对象key
         * @return  T
        </T> */
        @AnyThread
        @Throws(Exception::class)
        fun getBean(response: Response): JSONObject? {
            val body = response.body() ?: return null
            return JsonManager.parseJsonObject(body.string())

        }

        /*****
         * @param response 請求對象
         * @param clazz         返回实例对象类型
         * @param key     需要处理对象key
         * @return T
        </T> */
        @AnyThread
        @Throws(Exception::class)
        fun <T> getListBean(response: Response, clazz: Class<T>, key: String): List<T>? {
            val body = response.body() ?: return null
            val s = JsonManager.parseJsonObject(body.string())
            if (s.getBoolean("success")!!) {
                return JsonManager.jsonToList(s?.get(key).toString(), clazz)
            } else {
                throw IllegalStateException(response.message())
            }
        }
    }

}
