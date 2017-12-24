package com.example.aac.kotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.alibaba.fastjson.TypeReference
import com.example.aac.java.data.activity.bean.UserBean
import com.example.aac.utils.JsonCallback
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestDataViewModel(application: Application) : AndroidViewModel(application) {
    private val data = MutableLiveData<String>()
    private val liveData = MutableLiveData<String>()
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
  /*  private val liveData = MutableLiveData<UserBean>()

    fun getDatas(context: Context, param: String): LiveData<UserBean> {
        OkGo.get<UserBean>("url").tag(context)
                .params("param", param)
                .execute(object : JsonCallback<UserBean>("s", UserBean::class.java) {
                 override fun onSuccess(response: Response<UserBean>) {
                     liveData.value = response.body()
            }

                 override fun onError(response: Response<UserBean>) {
                 super.onError(response)
                     liveData.value = null
            }
        })
        return liveData
    }*/
 /*
   private val listData = MutableLiveData<List<UserBean>>()
    fun getListData(context: Context, param: String, page: Int): LiveData<List<UserBean>> {
        val typeReference = object : TypeReference<UserBean>(){}
        OkGo.get<List<UserBean>>("url")
                .tag(context)
                .params("param", param)
                .params("page", page)
                .execute(object : JsonCallback<List<UserBean>>("key", typeReference.type) {
                    override fun onSuccess(response: Response<List<UserBean>>) {
                        listData.value = response.body()
                    }

                    override fun onError(response: Response<List<UserBean>>) {
                        super.onError(response)
                        listData.value = null
                    }
                })
        return listData
    }*/

}
