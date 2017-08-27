package com.example.aac.data.fragment.list.custom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.aac.expansion.custom.AacCustomListFragment;
import com.aac.module.pres.RequiresPresenter;
import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aac.R;

import java.util.List;


/**
 * Created by yangc on 2017/8/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
@RequiresPresenter(TestCustomFragmentPresenter.class)
public class TestCustomListFragment extends AacCustomListFragment<TestCustomFragmentPresenter, String> {


    PullRefreshLayout pullToRefreshView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pullToRefreshView=$(view,R.id.swipeRefreshLayout);
        //自定义下拉刷新
       pullToRefreshView.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               getBaseOnRefresh();//回调中调用父类实现方法，踢可以完成刷新
           }
       });
        setStartLoadMore(true);
    }
    @Override
    public int getContentLayout() {
        return R.layout.test_cuutom_layout;
    }
    @Override
    public int setGridSpanCount() {
        return 3;
    }
    @Override
    protected boolean setOpenLazyLoad() {
        return true;
    }

    @Override
    public void setData(@NonNull List<String> data) {
        super.setData(data);
        pullToRefreshView.setRefreshing(false);
    }

    @Override
    public int getItemLayout() {
        return android.R.layout.simple_list_item_2;
    }
    @Override
    public void convertViewHolder(BaseViewHolder helper, String item) {
        helper.setText(android.R.id.text1, item);
        helper.setText(android.R.id.text2, item);
    }

}
