package com.example.aac.java.data.test.adapter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.aac.java.data.test.bean.Test

import java.util.ArrayList

/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 */
class  TestAdapter : BaseMultiItemQuickAdapter<Test, BaseViewHolder>(ArrayList()) {

    init {
        //addItemType();
    }

    override fun convert(helper: BaseViewHolder, item: Test) {

    }
}
