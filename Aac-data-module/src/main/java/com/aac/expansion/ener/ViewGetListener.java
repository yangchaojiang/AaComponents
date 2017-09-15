package com.aac.expansion.ener;

import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.helper.loadviewhelper.load.LoadViewHelper;

/**
 * Created by yangc on 2017/9/15.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  列表数据view 接口
 */


public interface ViewGetListener<M> {
    /***
     * 获取当前的分页数
     ***/
    int getCurPage();

    /**
     * 内容布局
     **/
    int getContentLayout();

    /**
     * 获取RecyclerView
     **/
    RecyclerView getRecyclerView();

    /**
     * 获取数据适配器实例
     **/
    BaseQuickAdapter getAdapter();

    /***
     * 获取加载管理类
     */
    LoadViewHelper getViewLoadHelper();

    /***
     * 设置网格中的列数
     * 子类重写该方法 大于1 使用网格布局 否则L是list
     */
    int setGridSpanCount();

    /***
     * 获取item数据layoutId
     **/
    int getItemLayout();

    /****
     * BaseViewHolder 实现item 布局内容
     *
     * @param helper BaseViewHolder
     * @param item   数据
     **/
    void convertViewHolder(BaseViewHolder helper, M item);


}
