package com.example.aac.kotlin.activity

import com.aac.expansion.list.AacListPresenter
import com.example.aac.kotlin.viewmodel.TestDataViewModel

/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestListPresenter : AacListPresenter<TestListActivity, String>(){
    private  var viewModel: TestDataViewModel?=null
    override fun onCreate() {
        viewModel=getViewModel(TestDataViewModel::class.java)
        setLoadListData(1)
    }

    override fun setLoadListData(pager: Int) {
        viewModel?.getListData(pager)?.observe(view,dataSubscriber)
    }
}