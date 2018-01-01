package com.aac.expansion.ener;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by yangc on 2017/9/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  列表数据view 接口
 */


public interface ViewGetListener<M> extends ViewGetDataListener{
    /***
     * 获取当前的分页数
     * @return int
     ***/
    int getCurPage();

    /**
     * 获取RecyclerView
     * @return RecyclerView
     **/
    RecyclerView getRecyclerView();

    /**
     * 获取数据适配器实例
     * @return BaseQuickAdapter
     **/
    BaseQuickAdapter<M,BaseViewHolder> getAdapter();

    /***
     * 设置网格中的列数
     * 子类重写该方法 大于1 使用网格布局 否则L是list
     * @return int
     */
    int setGridSpanCount();

    /***
     * 获取item数据layoutId
     * @return int
     **/
    @LayoutRes
    int getItemLayout();

    /****
     * BaseViewHolder 实现item 布局内容
     *
     * @param helper BaseViewHolder
     * @param item   数据
     **/
    void convertViewHolder(BaseViewHolder helper, M item);


}
