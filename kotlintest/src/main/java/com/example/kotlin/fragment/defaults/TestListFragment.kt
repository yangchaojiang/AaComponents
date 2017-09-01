package com.example.kotlin.fragment.defaults

import android.os.Bundle
import android.view.View

import com.aac.expansion.list.AacListFragment
import com.aac.module.pres.RequiresPresenter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
@RequiresPresenter(TestFragmentPresenter::class)
class TestListFragment : AacListFragment<TestFragmentPresenter, String>() {

    override fun getItemLayout(): Int {
        return android.R.layout.simple_list_item_2
    }

    override fun setGridSpanCount(): Int {
        return 3
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStartLoadMore(true)
    }

    override fun convertViewHolder(helper: BaseViewHolder, item: String) {
        helper.setText(android.R.id.text1, item)
        helper.setText(android.R.id.text2, item)
    }

    override fun setOpenLazyLoad(): Boolean {
        return true
    }
}
