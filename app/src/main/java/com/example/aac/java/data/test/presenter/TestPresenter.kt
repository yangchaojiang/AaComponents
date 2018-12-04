package com.example.aac.java.data.test.presenter

import com.aac.expansion.list.AacMultiListAPresenter

import com.example.aac.java.data.test.model.TestViewModel
import com.example.aac.java.data.test.ui.TestActivity
import com.example.aac.java.data.test.bean.Test;


/**
* Created by yangc on 2018/10/15 18:14:55
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
class TestPresenter : AacMultiListAPresenter<TestActivity, Test> (){
    private lateinit var mTest: TestViewModel
    public override fun onCreate() {
        super.onCreate()
        mTest = getViewModel(TestViewModel::class.java)
     }

 fun lazyLoad() {
      }
    companion object {
        val TAG = TestPresenter::class.java.name
    }
}
