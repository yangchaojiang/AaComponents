package com.aac.expansion.list;


import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * Deprecated: 数据父类
 */

@RequiresPresenter(AacDataAPresenter.class)
public abstract class AacListActivity<P extends AacListPresenter, M> extends AacActivity<P>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ViewGetListener<M> {
    private RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefresh;
    private int daraPage = 1;
    private LoadViewHelper helper;

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
        swipeRefresh.setOnRefreshListener(this);
        adapter.bindToRecyclerView(recyclerView);
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
        if (helper != null) {
            helper.onDestroy();
            helper = null;
        }
    }


    @Override
    public void onRefresh() {
        daraPage = 1;
        getPresenter().setLoadData(daraPage);
    }

    @Override
    public void onLoadMoreRequested() {
        daraPage += 1;
        getPresenter().setLoadData(daraPage);
    }

    /***
     * 设置数据
     * @param data data
     * **/
    public void setData(@NonNull List<M> data) {
        if (daraPage < 2) {
            if (!swipeRefresh.isRefreshing()) {
                helper.showContent();
            } else {
                setRefreshing(false);
            }
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            if (data.isEmpty()) {
                helper.showEmpty();
            }
        } else {
            if (data.isEmpty()) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }
        adapter.addData(data);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.aac_recycle_view;
    }

    @Override
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
    public void initLoadHelper(@NonNull View view) {
        helper = new LoadViewHelper(view);
    }

    /***
     * 显示数据加载view
     **/
    @Override
    public void showLoadView() {
        if (helper != null) {
            helper.showLoading();
        }
    }

    @Override
    public void showErrorView() {
        if (helper != null) {
            helper.showError();
        }
    }

    @Override
    public void showContentView() {
        if (helper != null) {
            helper.showContent();
        }
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
    public BaseQuickAdapter getAdapter() {
        return adapter;
    }

    /***
     * 获取加载管理类
     */
    @Override
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }

    /***
     * 进入页面开启是否下拉刷新
     *
     * @param setRefreshing true 开始刷新 false 停滞
     **/
    public void setRefreshing(final boolean setRefreshing) {
        if (helper != null) {
            helper.showContent();
        }
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
    private BaseQuickAdapter adapter = new BaseQuickAdapter<M, BaseViewHolder>(getItemLayout()) {
        @Override
        protected void convert(BaseViewHolder helper, M item) {
            convertViewHolder(helper, item);
        }
    };

}
