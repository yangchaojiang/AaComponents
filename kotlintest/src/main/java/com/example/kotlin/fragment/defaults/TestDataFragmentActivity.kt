package com.example.kotlin.fragment.defaults

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aac.module.pres.RequiresPresenter

import com.aac.module.ui.AacActivity
import com.example.kotlin.R

/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class TestDataFragmentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_list_fragment_view)

    }


}
