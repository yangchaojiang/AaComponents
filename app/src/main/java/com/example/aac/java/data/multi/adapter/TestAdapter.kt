package com.example.aac.java.data.multi.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.aac.java.data.multi.bean.Multi

import java.util.ArrayList

/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
class TestAdapter : BaseMultiItemQuickAdapter<Multi, BaseViewHolder>(ArrayList()) {

    init {
        //addItemType();
    }
    override fun convert(helper: BaseViewHolder, item: Multi) {

    }
}
