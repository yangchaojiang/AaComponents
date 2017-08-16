package com.example.aac.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.SystemClock;
import android.util.Log;

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
                data.postValue("撒的阿萨德");
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
                int s = (page-1)*40;
                List<String> list = new ArrayList<>();
                for (int i=s; i < 40*page; i++) {
                    list.add("数据：" + i);
                }
                listData.postValue(list);
            }
        });
        s.start();
        return listData;
    }
}
