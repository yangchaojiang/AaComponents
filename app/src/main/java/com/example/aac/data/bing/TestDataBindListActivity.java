package com.example.aac.data.bing;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aac.expansion.list.AacListActivity;
import com.aac.module.pres.RequiresPresenter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aac.R;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

@RequiresPresenter(TestDataBindListPresenter.class)
public class TestDataBindListActivity extends AacListActivity<TestDataBindListPresenter, String> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setLoadMore(true);
          setRefreshing(true);

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getSupportActionBar().setTitle("列表数据");
    }
    @Override
    public int getItemLayout() {
        return android.R.layout.simple_list_item_2;
    }
    @Override
    public int getContentLayout() {
        return R.layout.test_data_list_actvity;
    }
    @Override
    public void convertViewHolder(BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, item);
        helper.setText(android.R.id.text2, item);
    }


}
