package com.example.aac.java.data.multi.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.aac.expansion.list.AacListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aac.expansion.list.AacMultiListActivity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.aac.module.pres.RequiresPresenter;
import com.example.aac.R;
import com.example.aac.java.data.multi.adapter.TestAdapter;
import com.example.aac.java.data.multi.presenter.MultiPresenter;
import com.example.aac.java.data.multi.bean.Multi;

/**
* Created by yangc on 2018/10/15 11:08:53
* E-Mail:yangchaojiang@outlook.com
* Deprecated:
**/
@RequiresPresenter(MultiPresenter.class)
public class MultiActivity extends AacMultiListActivity<MultiPresenter, Multi> {
    private TestAdapter mTestAdapter;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity,MultiActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setLoadMore(true);
    }


    @Override
     public int setGridSpanCount() {
     return 3;
     }

    @NonNull
    @Override
    public TestAdapter getMultiAdapter() {
        return mTestAdapter;
    }

}
