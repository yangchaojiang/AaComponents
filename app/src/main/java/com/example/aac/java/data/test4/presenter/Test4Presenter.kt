package com.example.aac.java.data.test4.presenter

import com.aac.expansion.list.AacMultiListFPresenter
import com.example.aac.java.data.test4.model.Test4ViewModel
import com.example.aac.java.data.test4.ui.Test4Fragment
import com.example.aac.java.data.test4.bean.Test4;


/**
* Created by yangc on 2018/10/15 18:15:15
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
class Test4Presenter : AacMultiListFPresenter<Test4Fragment, Test4> (){
    private lateinit var mTest4: Test4ViewModel
    public override fun onCreate() {
        super.onCreate()
        mTest4 = getViewModel(Test4ViewModel::class.java)
     }

  override  fun lazyLoad() {
      }
    companion object {
        val TAG = Test4Presenter::class.java.name
    }
}
