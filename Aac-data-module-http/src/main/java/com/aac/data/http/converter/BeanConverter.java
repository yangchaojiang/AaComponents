package com.aac.data.http.converter;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.convert.Converter;
import com.yutils.JsonManager;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author  yangc
 * date 2017/12/22
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class BeanConverter<T> implements Converter<T> {

    private Type type;
    private Class<T> tClass;
    private final String key;

    /*****
     * @param tClass         返回实例对象类型
     * @param key     需要处理对象key
     */
    public BeanConverter(@NonNull String key, @NonNull Class<T> tClass) {
        this.key = key;
        this.tClass = tClass;

    }

    /*****
     * @param type         返回实例对象类型
     * @param key     需要处理对象key
     * set   MYTypeReference typeReference = ic_new MYTypeReference<JsonBean>>() {};
     * set   val typeReference =object: MYTypeReference<List<BbsClassBean>>() { }
    </T> */
    public BeanConverter(@NonNull String key, @NonNull Type type) {
        this.key = key;
        this.type = type;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            throw new Throwable(response.message());
        }
        String data = body.string();
        JSONObject jsonObject = JsonManager.parseJsonObject(data);
        if (jsonObject.getIntValue("errcode") == 0) {
            if (tClass != null) {
                if (key.equals("-1")) {
                    return JsonManager.jsonToBean(data, tClass);
                } else {
                    return jsonObject.getObject(key, tClass);
                }
            } else if (type != null) {
                return JsonManager.jsonToBean(key.equals("-1") ? data : jsonObject.get(key).toString(), type);
            } else {
                throw new Throwable(jsonObject.getString("errmsg"));
            }
        } else {
            throw new Throwable(jsonObject.getString("errmsg"));
        }

    }
}
