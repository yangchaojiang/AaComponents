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
import com.aac.expansion.ener.ViewGetListener;
import com.aac.module.ui.AacFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author yangc
 * date 2017/8/14
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 列表Fragment 支持懒加载， 子类重写setOpenLazyLoad方法，开启懒加载
 */

public abstract class AacListFragment<P extends AacListFragmentPresenter, M> extends AacFragment<P>
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, ViewGetListener<M> {
    private RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefresh;
    private int daraPage = 1;
    private LoadViewHelper helper;
    private AacBaseQuickAdapter adapter;
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        recyclerView = view.findViewById(R.id.recyclerView);
        isInit = true;
        if (setGridSpanCount() <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), setGridSpanCount()));
        }
        swipeRefresh.setOnRefreshListener(this);
        adapter = new AacBaseQuickAdapter(this);
        adapter.bindToRecyclerView(recyclerView);
        initLoadHelper(swipeRefresh);
        //初始化的时候去加载数据*
        if (setOpenLazyLoad()) {
            isCanLoadData();
        }
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
    public int getContentLayoutId() {
        return R.layout.aac_include_recycle_view;
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @CallSuper
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (setOpenLazyLoad()) {
            isCanLoadData();
        }
        ;
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            getPresenter().lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                getPresenter().stopLoad();
            }
        }
    }

    /**
     * 开启懒加载
     *
     * @return setOpenLazyLoad true  开启 false
     **/
    protected boolean setOpenLazyLoad() {
        return false;
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

    public void setData(@NonNull List<M> data) {
        if (daraPage < 2) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
            setRefreshing(false);
            adapter.addData(data);
            if (data.isEmpty()) {
                helper.showEmpty();
            } else {
                helper.showContent();
            }
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

    /***
     * 错误
     * @param  e 错误
     **/
    @Override
    public void setError(Throwable e) {
        if (daraPage < 2) {
            helper.showError();
        } else {
            adapter.loadMoreFail();
        }
    }

    @Override
    public void initLoadHelper(@NonNull View view) {
        helper = new LoadViewHelper(view);
        showLoadView();
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
    public BaseQuickAdapter<M,BaseViewHolder> getAdapter() {
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
     * @param refreshing 是否刷新 true   false 则停止
     **/
    public void setRefreshing(boolean refreshing) {
        swipeRefresh.setRefreshing(refreshing);
        if (helper != null) {
            helper.showContent();
        }
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
            weakReference=new WeakReference<>(aacListFragment);

        }

        @Override
        protected void convert(BaseViewHolder helper, M item) {
            if (weakReference.get()==null) return;
            convertViewHolder(helper, item);
        }
    }


}

