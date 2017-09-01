package com.example.kotlin.activity

import com.aac.expansion.data.AacDataAPresenter
import com.example.kotlin.viewmodel.TestDataViewModel

/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestDataPresenter : AacDataAPresenter<TestDataActivity, String>() {

    var viewModel: TestDataViewModel?=null

    override fun onCreate() {
        super.onCreate()
        viewModel=getViewModel(TestDataViewModel::class.java)
    }
    override fun onCreateView() {
        super.onCreateView()
        viewModel?.getData( )?.observe(view,dataSubscriber)
    }




}