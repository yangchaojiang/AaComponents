package com.example.kotlin.activity

import android.os.Bundle
import com.aac.expansion.list.AacListActivity
import com.aac.module.pres.RequiresPresenter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlin.R

@RequiresPresenter(TestListPresenter::class)
class TestListActivity : AacListActivity<TestListPresenter, String>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLoadMore(true)
       // setRefreshing(true)
        showLoadView()
    }
    override fun convertViewHolder(helper: BaseViewHolder?, item: String?) {
        helper?.setText(android.R.id.text1,item)
        helper?.setText(android.R.id.text2,"索引${helper.adapterPosition}")
    }

    override fun getItemLayout(): Int {
        return  android.R.layout.simple_expandable_list_item_2
    }

    override fun getContentView(): Int {
      return  R.layout.activity_test_list
    }


}
