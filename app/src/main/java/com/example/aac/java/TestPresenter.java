package com.example.aac.java;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.aac.data.http.converter.BeanConverter;
import com.aac.data.http.utils.AacUtils;
import com.aac.module.ui.AacPresenter;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.model.HttpParams;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestPresenter extends AacPresenter<MainActivity> {
    public static final String TAG = "TestPresenter";


    @Override
    protected void onCreateView() {
        super.onCreateView();
        Log.d(TAG, "onCreateView");
    }

    public LiveData<String> sss() {
        HttpParams httpParams = new HttpParams();
       return AacUtils.httpLiveGet("sss"
                , httpParams
                , new BeanConverter<String>("-1", String.class));
    }

    public Flowable<List<String>> sss2() {
        HttpParams params = new HttpParams();
        TypeReference typeReference = new TypeReference<List<String>>(){};
        return AacUtils.httpRxGet("sss"
                , params
                , new BeanConverter<>("-1",typeReference.getType()));
    }


}
