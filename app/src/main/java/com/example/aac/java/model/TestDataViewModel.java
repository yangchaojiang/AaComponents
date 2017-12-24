package com.example.aac.java.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;


import com.alibaba.fastjson.TypeReference;
import com.example.aac.java.data.activity.bean.UserBean;
import com.example.aac.utils.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class TestDataViewModel extends AndroidViewModel {
    private MutableLiveData<String> data = new MutableLiveData<>();
    private MutableLiveData<List<String>> listData = new MutableLiveData<>();
    private MutableLiveData<UserBean> liveData = new MutableLiveData<>();
    public TestDataViewModel(Application application) {
        super(application);
        Log.d("TestDataViewModel", "TestDataViewModel");

    }

    public LiveData<String> getData() {
        Log.d("TestDataViewModel", data.getValue() + "");
        Thread s = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                data.postValue(null);
                //data.postValue("获取数据");
            }
        });
        s.start();
        return data;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("TestDataViewModel", "onCleared");
    }

    public LiveData<List<String>> getListData(final int page) {
        Thread s = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                int s = (page - 1) * 40;
                List<String> list = new ArrayList<>();
                for (int i = s; i < 40 * page; i++) {
                    list.add("数据：" + i);
                }
                listData.postValue(list);
            }
        });
        s.start();
        return listData;
    }
}
