package com.example.kotlin.activity

import com.aac.expansion.list.AacListActivityPresenter
import com.example.kotlin.viewmodel.TestDataViewModel

/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestListPresenter : AacListActivityPresenter<TestListActivity, String>(){
    private  var viewModel: TestDataViewModel?=null
    override fun onCreate() {
        super.onCreate()
        viewModel=getViewModel(TestDataViewModel::class.java)
        viewModel?.getListData(1)?.observe(view,dataSubscriber)
    }
    override fun setLoadData(pager: Int) {
        viewModel?.getListData(pager)?.observe(view,dataSubscriber)
    }
}