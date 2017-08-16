package com.acc.expansion.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acc.expansion.R;
import com.acc.module.ui.BaseLifecycleFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public abstract class BaseListFragment<P extends BaseListFragmentPresenter, M> extends BaseLifecycleFragment<P>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private QuickDataAdapter adapter;
    private int daraPage = 1;
    private LoadViewHelper helper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentView() == 0) {
            return inflater.inflate(R.layout.acc_recycle_view, container, false);
        } else {
            return inflater.inflate(getContentView(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefresh = $(view, R.id.swipeRefresh);
        recyclerView = $(view, R.id.recyclerView);
        if (setGridSpanCount() == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), setGridSpanCount()));
        }
        swipeRefresh.setOnRefreshListener(this);
        adapter = new QuickDataAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.acc_view_list_con_empty);
        helper = new LoadViewHelper(swipeRefresh);
        helper.showLoading();
    }

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
        if (helper!=null){
            helper.onDestroy();
            helper=null;
        }
    }

    @Override
    public void onRefresh() {
        daraPage = 0;
        getPresenter().refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        daraPage += 1;
        getPresenter().setPageData(daraPage);
    }

    public void setData(@NonNull List<M> data) {
        if (daraPage < 2) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            setRefreshing(false);
            adapter.addData(data);
            helper.showContent();
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
     **/
    public void setError(Throwable e) {
        if (daraPage < 2) {
            helper.showError();
        } else {
            adapter.loadMoreFail();
        }

    }


    /**
     * 获取RecyclerView
     **/
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获取数据适配器实例
     **/
    public  QuickDataAdapter getAdapter() {
        return adapter;
    }
    /***
     * 获取加载管理类
     */
    public LoadViewHelper getHelper() {
        return helper;
    }

    /***
     * 是@param refreshing 是否刷新 true   false 则停止
     **/
    public void setRefreshing(boolean refreshing) {
        swipeRefresh.setRefreshing(refreshing);
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
     * 设置网格中的列数
     * 子类重写该方法 大于0 使用网格布局 否则L是list
     */
    public int setGridSpanCount() {
        return 0;
    }

    /***
     * 获取item数据layoutId
     **/
    public abstract int getItemLayout();

    /****
     * BaseViewHolder 实现item 布局内容
     *
     * @param helper BaseViewHolder
     * @param item   数据
     **/
    public abstract void convertViewHolder(BaseViewHolder helper, M item);

    /***
     * 内容布局  自定义布局
     **/
    public int getContentView() {
        return 0;
    }

    /***
     * 数据式适配器
     ****/
    private class QuickDataAdapter extends BaseQuickAdapter<M, BaseViewHolder> {
        public QuickDataAdapter() {
            super(getItemLayout());
        }

        @Override
        protected void convert(BaseViewHolder helper, M item) {
            convertViewHolder(helper, item);
        }
    }


}

