package com.example.kotlin.fragment.defaults

import com.aac.expansion.list.AacListFragmentPresenter
import com.example.kotlin.viewmodel.TestDataViewModel

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestFragmentPresenter : AacListFragmentPresenter<TestListFragment, String>() {
    private var viewModel: TestDataViewModel? = null

    override fun onCreateView() {
        super.onCreateView()
        viewModel = getViewModel(TestDataViewModel::class.java)
    }

    override fun lazyLoad() {
        viewModel!!.getListData(1).observe(view, dataSubscriber)
    }

    override fun setLoadData(pager: Int) {
        viewModel!!.getListData(pager).observe(view, dataSubscriber)
    }

    companion object {
        val TAG = "TestFragmentPresenter"
    }
}
