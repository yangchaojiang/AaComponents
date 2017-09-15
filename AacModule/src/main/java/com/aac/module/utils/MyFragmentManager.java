package com.aac.module.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by yangc on 2017/9/11.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: fragment 管里类
 */

public class MyFragmentManager {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private int resId;
    /****
     * 初始化
     *
     * @param fragmentManager fragment 容器
     * @param resId           容器布局
     * @param data            list列表数据
     **/
    public MyFragmentManager(@NonNull List<Fragment> data,@NonNull FragmentManager fragmentManager, @IdRes int resId) {
        fragmentList = new ArrayList<>();
        this.fragmentManager = fragmentManager;
        this.resId = resId;
        addAll(data);
    }

    /****
     * 初始化
     *
     * @param fragmentManager fragment 容器
     * @param resId           容器布局
     **/
    public MyFragmentManager(@NonNull FragmentManager fragmentManager, @IdRes int resId) {
        fragmentList = new ArrayList<>();
        this.fragmentManager = fragmentManager;
        this.resId = resId;
    }

    /****
     * 添加列表
     *
     * @param data list列表数据
     ***/
    private void addAll(List<Fragment> data) {
        fragmentList.addAll(data);
        FragmentTransaction b = fragmentManager.beginTransaction();
        for (Fragment item : fragmentList) {
            b.add(resId, item);
        }
        for (Fragment item : fragmentList) {
            b.hide(item);
        }
        b.show(fragmentList.get(0));
        b.commitAllowingStateLoss();
    }
    /****
     * 添加item
     *
     * @param fragment 添加单个
     ***/
    public void add(Fragment fragment) {
        fragmentList.add(fragment);
    }
    /****
     * 插入item
     *
     * @param  index 插入索引
     * @param fragment item
     ***/
    public void install(int index,Fragment fragment) {
        fragmentList.set(index,fragment);
    }

    /***
     * 显示当前fragment 其他隐藏
     *
     * @param index 索引
     **/
    public void show(int index) {
        FragmentTransaction b = fragmentManager.beginTransaction();
        b.show(fragmentList.get(index));
        b.commitAllowingStateLoss();
    }
    /***
     * 显示当前fragment 其他隐藏
     *
     * @param index 索引
     **/
    public void updateShow(int index) {
        FragmentTransaction b = fragmentManager.beginTransaction();
        for (Fragment item : fragmentList) {
            b.hide(item);
        }
        b.show(fragmentList.get(index));
        b.commitAllowingStateLoss();
    }

    /***
     * 隐藏当前fragment
     *
     * @param index 索引
     **/
    public void hide(int index) {
        FragmentTransaction b = fragmentManager.beginTransaction();
        b.show(fragmentList.get(index));
        b.commitAllowingStateLoss();
    }
    /***
     * 隐藏当前fragment
     *
     * @param index 索引
     **/
    public void replace(int index) {
        FragmentTransaction b = fragmentManager.beginTransaction();
        b.replace(resId,fragmentList.get(index));
        b.commitAllowingStateLoss();
    }

    /***
     * 全部隐藏当前fragment
     **/
    public void hideAll() {
        FragmentTransaction b = fragmentManager.beginTransaction();
        for (Fragment item : fragmentList) {
            b.hide(item);
        }
        b.commitAllowingStateLoss();
    }

    /***
     * 释放资源
     * **/
    public void onDestroy() {
        if (fragmentList != null) {
            fragmentList.clear();
            fragmentList = null;
        }
        fragmentManager = null;
    }

}
