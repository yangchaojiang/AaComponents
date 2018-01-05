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
import com.aac.expansion.data.AacDataFPresenter;
import com.aac.expansion.data.AacDataFragment;
import com.aac.expansion.ener.ViewGetListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 列表Fragment 支持懒加载， 子类重写setOpenLazyLoad方法，开启懒加载
 */

public abstract class AacListFragment<P extends AacDataFPresenter, M> extends AacDataFragment<P, List<M>>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ViewGetListener<M> {
    private RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefresh;
    private int daraPage = 1;
    private AacBaseQuickAdapter adapter;

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        recyclerView = view.findViewById(R.id.recyclerView);
        if (setGridSpanCount() <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), setGridSpanCount()));
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setRefreshing(false);
        adapter = new AacBaseQuickAdapter(this);
        adapter.bindToRecyclerView(recyclerView);
    }

    @Override
    public void initLoadHelper(@NonNull View view) {
        super.initLoadHelper(view);
        adapter.setEmptyView(getViewLoadHelper().getEmptyLayoutId());
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

    /***
     * 设置数据
     * @param  data data
     * **/
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

    @Override
    public void setError(Throwable e) {
        if (daraPage < 2) {
            showErrorView();
        } else {
            adapter.loadMoreFail();
        }
    }

    public void setDaraPage(int daraPage) {
        this.daraPage = daraPage;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.aac_include_recycle_view;
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
     * @param setRefreshing 是否刷新 true   false 则停止
     **/
    public void setRefreshing(boolean setRefreshing) {
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
    private class AacBaseQuickAdapter extends BaseQuickAdapter<M, BaseViewHolder> {
        private WeakReference<AacListFragment> weakReference;

        private AacBaseQuickAdapter(AacListFragment aacListFragment) {
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

