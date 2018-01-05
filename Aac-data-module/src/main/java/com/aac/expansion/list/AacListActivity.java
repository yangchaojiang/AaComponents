package com.aac.expansion.list;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aac.expansion.R;
import com.aac.expansion.data.AacDataAPresenter;
import com.aac.expansion.data.AacDataActivity;
import com.aac.expansion.ener.ViewGetListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 数据父类
 */

public abstract class AacListActivity<P extends AacDataAPresenter, M> extends AacDataActivity<P, List<M>>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ViewGetListener<M> {
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefresh;
    private AacBaseQuickAdapter adapter;
    private int daraPage = 1;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        if (setGridSpanCount() <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, setGridSpanCount()));
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        swipeRefresh.setOnRefreshListener(this);
        adapter = new AacBaseQuickAdapter(this);
        adapter.bindToRecyclerView(recyclerView);
        //检查是否满一屏，如果不满足关闭loadMore
        adapter.disableLoadMoreIfNotFullPage();
        initLoadHelper(swipeRefresh);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.getData().clear();
            adapter.setOnLoadMoreListener(null, recyclerView);
        }
        if (swipeRefresh != null) {
            swipeRefresh.setRefreshing(false);
            swipeRefresh.setOnRefreshListener(null);
        }
    }

    @Override
    public void initLoadHelper(@NonNull View view) {
        super.initLoadHelper(view);
        adapter.setEmptyView(getViewLoadHelper().getEmptyLayoutId());
    }

    @Override
    public void onRefresh() {
        daraPage = 1;
        getPresenter().setLoadListData(daraPage);
    }

    @Override
    public void onLoadMoreRequested() {
        daraPage += 1;
        getPresenter().setLoadListData(daraPage);
    }

    /***
     * 设置数据
     * @param data data
     * **/
    @Override
    public void setData(@NonNull List<M> data) {
        if (daraPage < 2) {
            adapter.isUseEmpty(true);
            if (swipeRefresh.isRefreshing()) {
                setRefreshing(false);
            }
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            adapter.addData(data);

        } else {
            if (data.isEmpty()) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
            adapter.addData(data);
        }


    }

    /***
     * 错误
     * @param  e 错误
     **/
    @Override
    public void setError(Throwable e) {
        if (daraPage < 2) {
            showErrorView();
        } else {
            adapter.loadMoreFail();
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.aac_recycle_view;
    }

    public void setDaraPage(int daraPage) {
        this.daraPage = daraPage;
    }

    @Override
    public int getCurPage() {
        return daraPage;
    }

    /**
     * 获取RecyclerView
     **/
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获取数据适配器实例
     **/
    @Override
    public BaseQuickAdapter<M, BaseViewHolder> getAdapter() {
        return adapter;
    }


    /***
     * 进入页面开启是否下拉刷新
     *
     * @param setRefreshing true 开始刷新 false 停滞
     **/
    public void setRefreshing(final boolean setRefreshing) {
        showContentView();
        adapter.isUseEmpty(false);
        swipeRefresh.postDelayed(() -> {
            if (swipeRefresh == null) return;
            swipeRefresh.setRefreshing(setRefreshing);
            if (setRefreshing) {
                onRefresh();
            }
        }, 200);

    }

    /***
     * 设置网格中的列数
     * 子类重写该方法 大于1 使用网格布局 否则L是list
     */
    @Override
    public int setGridSpanCount() {
        return 1;
    }

    /**
     * 是否启用分页  默认不启用
     *
     * @param enable true  启用 false 不启用
     */
    public void setLoadMore(boolean enable) {
        if (enable) {
            adapter.setOnLoadMoreListener(this, recyclerView);
        }
    }

    /***
     * 数据式适配器
     ****/
    private class AacBaseQuickAdapter extends BaseQuickAdapter<M, BaseViewHolder> {
        private WeakReference<AacListActivity> weakReference;

        private AacBaseQuickAdapter(AacListActivity aacListFragment) {
            super(getItemLayout());
            weakReference = new WeakReference<>(aacListFragment);
        }

        @Override
        protected void convert(BaseViewHolder helper, M item) {
            if (weakReference.get() == null) return;
            convertViewHolder(helper, item);
        }
    }
}
