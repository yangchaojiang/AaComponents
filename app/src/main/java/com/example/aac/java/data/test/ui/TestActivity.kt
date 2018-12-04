package com.example.aac.java.data.test.ui
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.aac.expansion.list.AacMultiListActivity

import com.chad.library.adapter.base.BaseViewHolder
import com.aac.module.pres.RequiresPresenter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.example.aac.R
import com.example.aac.java.data.test.presenter.TestPresenter
import com.example.aac.java.data.test.bean.Test
import com.example.aac.java.data.test.adapter.TestAdapter

/**
* Created by yangc on 2018/10/15 18:14:55
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
 @RequiresPresenter(TestPresenter::class)
 class TestActivity : AacMultiListActivity<TestPresenter, Test>() {

 private val mTestAdapter : TestAdapter = TestAdapter()

   override fun getMultiAdapter(): BaseMultiItemQuickAdapter<Test,BaseViewHolder> = mTestAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        // setLoadMore(true)
     }

     companion object {
         fun startActivity(activity: Activity) {
             val intent = Intent(activity, TestActivity::class.java)
             activity.startActivity(intent)
         }
     }
 }

