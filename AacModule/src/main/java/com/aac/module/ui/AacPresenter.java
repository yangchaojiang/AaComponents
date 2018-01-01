package com.aac.module.ui;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * author yangc
 * date 2017/8/13
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  业务处理控制类
 *
 * @param <ActivityType> 与AacPresenter建立订阅View
 * @see AacPresenter
 */

public abstract class AacPresenter<ActivityType> implements LifecycleObserver {
    private Lifecycle lifecycle;
    private ActivityType view;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        view = null;
        lifecycle = null;
    }
    protected void onCreateView() {

    }

    public void onDestroyView() {

    }

    public void onViewCreated() {
    }
    protected void onResult(int requestCode, int resultCode, Intent data) {

    }

    protected void onSave(Bundle state) {

    }

    @NonNull
    public final ActivityType getView() {
        return view;
    }

    /***
     * 单例获取viewModel方式类型实例
     *
     * @param modelClass modelClass类型
     *@param  <ViewModelType>       ViewModelType
     * @return ViewModelType
     **/
    public <ViewModelType extends ViewModel> ViewModelType getInstanceViewModel(Class<ViewModelType> modelClass) {
        return new ViewModelProvider.NewInstanceFactory().create(modelClass);
    }

    /***
     * 获取数据viewModel方式类型实例
     *
     * @param modelClass modelClass类型
     *@param  <ViewModelType>       ViewModelType
     * @return ViewModelType
     **/
    public <ViewModelType extends ViewModel> ViewModelType getViewActivityModel(Class<ViewModelType> modelClass) {

        if (view instanceof Fragment) {
            return ViewModelProviders.of(((Fragment) view).getActivity()).get(modelClass);
        } else if (view instanceof FragmentActivity) {
            return ViewModelProviders.of((FragmentActivity) view).get(modelClass);
        } else {
            return getInstanceViewModel(modelClass);
        }
    }

    /***
     * 获取数据viewModel方式类型实例
     *
     * @param modelClass modelClass类型
     *@param  <ViewModelType>       ViewModelType
     * @return ViewModelType
     **/
    public <ViewModelType extends ViewModel> ViewModelType getViewModel(Class<ViewModelType> modelClass) {

        if (view instanceof Fragment) {
            return ViewModelProviders.of((Fragment) view).get(modelClass);
        } else if (view instanceof FragmentActivity) {
            return ViewModelProviders.of((FragmentActivity) view).get(modelClass);
        } else {
            return getInstanceViewModel(modelClass);
        }
    }

    /***
     * 获取数据viewModel方式类型实例
     *
     * @param modelClass modelClass类型
     *@param  <ViewModelType>       ViewModelType
     * @return ViewModelType
     **/
    public <ViewModelType extends ViewModel> ViewModelType getApplicationViewModel(Class<ViewModelType> modelClass) {

        if (view instanceof Fragment) {
            return new ViewModelProviders.DefaultFactory(((Fragment) view).getActivity().getApplication()).create(modelClass);
        } else if (view instanceof FragmentActivity) {
            return new ViewModelProviders.DefaultFactory(((Activity) view).getApplication()).create(modelClass);
        } else {
            return getInstanceViewModel(modelClass);
        }
    }

    /**
     * 返回是否当前生命中周期状态
     *
     * @param state Lifecycle.State
     * @return boolean
     **/
    public boolean isAtLeast(Lifecycle.State state) {
        return lifecycle.getCurrentState().isAtLeast(state);
    }

    void create(@NonNull ActivityType view) {
        this.view = view;
    }


}
