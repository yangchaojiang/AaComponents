package com.aac.expansion.custom;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aac.expansion.R;
import com.aac.expansion.data.BaseDataPresenter;
import com.aac.module.pres.RequiresPresenter;
import com.aac.module.ui.BaseLifecycleActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

import java.util.List;

/**
 * Created by yangc on 2017/8/14.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 自定义下拉刷新view
 */

@RequiresPresenter(BaseDataPresenter.class)
public abstract class BaseCustomListActivity<P extends BaseCustomLAPresenter, M> extends BaseLifecycleActivity<P>
        implements BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = BaseCustomListActivity.class.getName();
    private RecyclerView recyclerView;
    private QuickDataAdapter adapter;
    private int daraPage = 1;
    private LoadViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        recyclerView = $(R.id.recyclerView);
        if (setGridSpanCount() == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, setGridSpanCount()));
        }
        adapter = new QuickDataAdapter();
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEmptyView(R.layout.aac_view_list_con_empty);
        helper = new LoadViewHelper(recyclerView);
        Log.d(TAG, "onCreate");
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
     **/
    public void setError(Throwable e) {
        if (daraPage < 2) {
            helper.showError();
        } else {
            adapter.loadMoreFail();
        }
    }

    /***
     * 获取当前的分页数
     ***/
    public int getCurPage() {
        return daraPage;
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
    public QuickDataAdapter getAdapter() {
        return adapter;
    }

    /***
     * 获取加载管理类
     */
    public LoadViewHelper getViewLoadHelper() {
        return helper;
    }

    /***
     * 设置网格中的列数
     * 子类重写该方法 大于0 使用网格布局 否则L是list
     */
    public int setGridSpanCount() {
        return 0;
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

    /****
     * BaseViewHolder 实现item 布局内容
     *
     * @param helper BaseViewHolder
     * @param item   数据
     **/
    public abstract void convertViewHolder(BaseViewHolder helper, M item);

    /***
     * 获取item数据layoutId
     **/
    public abstract int getItemLayout();

    public abstract int getContentView();

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
