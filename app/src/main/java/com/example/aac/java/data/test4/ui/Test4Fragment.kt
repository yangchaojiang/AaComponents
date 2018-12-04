package com.example.aac.java.data.test4.ui
import android.os.Bundle
import android.view.View
import com.aac.expansion.list.AacMultiListFragment
import com.chad.library.adapter.base.BaseViewHolder
import com.aac.module.pres.RequiresPresenter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.example.aac.R
import com.example.aac.java.data.test4.presenter.Test4Presenter
import com.example.aac.java.data.test4.bean.Test4
import com.example.aac.java.data.test4.adapter.Test4Adapter

/**
* Created by yangc on 2018/10/15 18:15:14
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
 @RequiresPresenter(Test4Presenter::class)
 class Test4Fragment : AacMultiListFragment<Test4Presenter, Test4>() {

 private val mTest4Adapter : Test4Adapter = Test4Adapter()

   override fun getMultiAdapter(): BaseMultiItemQuickAdapter<Test4,BaseViewHolder> = mTest4Adapter

     override fun setOpenLazyLoad(): Boolean = true

     override fun setGridSpanCount(): Int = 1

     override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       //setStartLoadMore(true);
      }

    companion object {
         fun getInstance(param: String): Test4Fragment {
             val fragment = Test4Fragment()
             val bundle = Bundle()
             bundle.putString("param", param)
             fragment.arguments = bundle
             return fragment
         }
    }

 }

