package com.example.aac.kotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.SystemClock
import android.util.Log
/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestDataViewModel(application: Application) : AndroidViewModel(application) {
    private val data = MutableLiveData<String>()
    private val listData = MutableLiveData<List<String>>()
    init {
        Log.d("TestDataViewModel", "TestDataViewModel")
    }
    fun getData(): LiveData<String> {
        Log.d("TestDataViewModel", data.value + "")
        val s = Thread(Runnable {
            SystemClock.sleep(1000)
            data.postValue("撒的阿萨德")
        })
        s.start()
        return data
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TestDataViewModel", "onCleared")
    }

    fun getListData(page: Int): LiveData<List<String>> {
        val s = Thread(Runnable {
            SystemClock.sleep(2000)
            val s = (page - 1) * 40
            val list = (s..40 * page - 1).map { "数据：" + it }
            listData.postValue(list)
        })
        s.start()
        return listData
    }
}
