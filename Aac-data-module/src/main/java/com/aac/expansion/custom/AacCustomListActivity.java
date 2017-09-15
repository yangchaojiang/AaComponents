package com.aac.expansion.custom;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aac.expansion.R;
import com.aac.expansion.data.AacDataAPresenter;
import com.aac.expansion.ener.ViewGetListener;
import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.AacActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 自定义下拉刷新view
 */

@RequiresPresenter(AacDataAPresenter.class)
public abstract class AacCustomListActivity<P extends AacCustomALPresenter, M> extends AacActivity<P>
        implements BaseQuickAdapter.RequestLoadMoreListener,ViewGetListener<M> {
    private RecyclerView recyclerView;
    private QuickDataAdapter adapter;
    private int daraPage = 1;
    private LoadViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        recyclerView = $(R.id.recyclerView);
        if (setGridSpanCount() <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, setGridSpanCount()));
        }
        adapter = new QuickDataAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.aac_view_list_con_empty);
        helper = new LoadViewHelper(recyclerView);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.getData().clear();
            adapter.setOnLoadMoreListener(null, recyclerView);
        }
        if (helper != null) {
            helper.onDestroy();
            helper = null;
        }
    }
    /***
     * 子类刷新回调调用该方法
     * **/
    public  void onRefresh() {
        daraPage = 1;
        getPresenter().setLoadData(daraPage);
    }
    @Override
    public void onLoadMoreRequested() {
        daraPage += 1;
        getPresenter().setLoadData(daraPage);
    }

    public void setData(@NonNull List<M> data) {
        if (daraPage < 2) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
        } else {
            if (data.isEmpty()) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }
        adapter.addData(data);

    }


    /***
     * 错误
     * @param  e  错误
     **/
    public void setError(Throwable e) {
        if (daraPage < 2) {
            helper.showError();
        } else {
            adapter.loadMoreFail();
        }
    }

    @Override
    public int getCurPage() {
        return daraPage;
    }
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
    @Override
    public QuickDataAdapter getAdapter() {
        return adapter;
    }
    @Override
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }
    /**
     * 是否启用分页  默认不启用
     *
     * @param enable true  启用 false 不启用
     */
    public void setStartLoadMore(boolean enable) {
        if (enable) {
            adapter.setOnLoadMoreListener(this, recyclerView);
        }
    }

    /***
     * 数据式适配器
     ****/
    private class QuickDataAdapter extends BaseQuickAdapter<M, BaseViewHolder> {
          QuickDataAdapter() {
            super(getItemLayout());
        }

        @Override
        protected void convert(BaseViewHolder helper, M item) {
            convertViewHolder(helper, item);
        }
    }

}
