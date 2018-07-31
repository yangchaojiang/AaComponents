package com.aac.data.http.converter


import com.alibaba.fastjson.JSONObject
import com.lzy.okgo.convert.Converter
import com.yutils.JsonManager
import okhttp3.Response

/**
 * Created by yangc on 2017/10/7.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class JsonObjectConverter  : Converter<JSONObject> {
    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Throws(Throwable::class)
    override fun convertResponse(response: Response): com.alibaba.fastjson.JSONObject? {
        val body = response.body()
        if (body != null) {
            val s = JsonManager.parseJsonObject(body.string())
            return if (s.getIntValue("errcode") == 0) {
                s
            } else {
                if (s.getIntValue("errcode") > 0) {
                    throw IllegalStateException(s.getString("errmsg"))
                } else {
                    throw IllegalStateException(response.message())
                }
            }
        } else {
            throw IllegalStateException(response.message())
        }
    }
}
