package com.example.kotlin.activity

import com.aac.expansion.data.AacDataActivity
import com.aac.module.pres.RequiresPresenter
import com.example.kotlin.R
import kotlinx.android.synthetic.main.test_data_activity.*
/**
 * Created by yangc on 2017/8/27.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
@RequiresPresenter(TestDataPresenter::class)
public  class TestDataActivity : AacDataActivity<TestDataPresenter, String>(){

    override fun getContentLayoutId():Int=  R.layout.test_data_activity

    override fun setData(data: String) {
        textView.text=data
    }

    override fun setError(e: Throwable?) {

    }

}