package com.example.aac.java.data.test4.adapter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.aac.java.data.test4.bean.Test4

import java.util.ArrayList

/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 */
class  Test4Adapter : BaseMultiItemQuickAdapter<Test4, BaseViewHolder>(ArrayList()) {

    init {
        //addItemType();
    }

    override fun convert(helper: BaseViewHolder, item: Test4) {

    }
}
