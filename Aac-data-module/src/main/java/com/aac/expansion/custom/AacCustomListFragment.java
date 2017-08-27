package com.aac.expansion.custom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aac.expansion.R;
import com.aac.module.ui.AacFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 列表Fragment 支持懒加载， 子类重写setOpenLazyLoad方法，开启懒加载
 */

public abstract class AacCustomListFragment<P extends AacCustomLFPresenter, M> extends AacFragment<P>
        implements   BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView recyclerView;
    private QuickDataAdapter adapter;
    private int daraPage = 1;
    private LoadViewHelper helper;
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(getContentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = $(view, R.id.recyclerView);
        if (setGridSpanCount() == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), setGridSpanCount()));
        }
        adapter = new QuickDataAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.aac_view_list_con_empty);
        helper = new LoadViewHelper(recyclerView);
        helper.showLoading();
        isInit = true;
        /**初始化的时候去加载数据**/
        if (setOpenLazyLoad()) isCanLoadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.getData().clear();
            adapter.setOnLoadMoreListener(null, recyclerView);

        }
        if (helper!=null){
            helper.onDestroy();
            helper=null;
        }
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (setOpenLazyLoad()) isCanLoadData();
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
     * @return  setOpenLazyLoad true  开启 false
     **/
    protected    boolean  setOpenLazyLoad(){
        return false;
    }

    /***
     * 子类刷新回调调用该方法
     * **/
    public void getBaseOnRefresh() {
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
    public LoadViewHelper getLoadViewHelper() {
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
     * 内容布局id
     ***/
    public abstract int getContentLayout();
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

