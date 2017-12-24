package com.example.aac.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.yutils.JsonManager;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by yangc on 2017/10/7.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;
    private String key;

    public JsonCallback(String key, Class<T> clazz) {
        this.clazz = clazz;
        this.key = key;
    }
    public JsonCallback(String key, Type type) {
        this.type = type;
        this.key = key;
    }
    private JsonCallback(){

    }
    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        // 重要的事情说三遍，不同的业务，这里的代码逻辑都不一样，如果你不修改，那么基本不可用
        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        JSONObject s = JsonManager.parseJsonObject(body.string());
        if (s.getBoolean("success")) {
            if (clazz!=null){
                return JsonManager.jsonToBean(s.get(key).toString(),clazz);
            }else if (type!=null){
                return JsonManager.jsonToBean(s.get(key).toString(),type);
            }else {
                return JsonManager.jsonToBean(body.bytes());
            }
        } else {
            throw new IllegalStateException(response.message());
        }
    }


}
