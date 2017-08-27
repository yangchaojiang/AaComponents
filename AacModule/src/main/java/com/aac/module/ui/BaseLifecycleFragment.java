package com.aac.module.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aac.module.pres.PresenterBuilder;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public abstract class BaseLifecycleFragment<R extends BaseFragmentLifecycle> extends Fragment implements LifecycleRegistryOwner {
    private static final String TAG = BaseLifecycleFragment.class.getName();
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private R t = PresenterBuilder.fromViewClass(this.getClass());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleRegistry.addObserver(t);
        t.create(this, lifecycleRegistry);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        t.onCreateView();
        t.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        t.onCreateView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleRegistry.removeObserver(t);
        lifecycleRegistry = null;
        t = null;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    /***
     * 得到对应业务处理类
     * @return     R
     **/
   public   R getPresenter() {
        return t;
    }

    /**
     * 返回是否当前生命中周期状态
     *
     * @param state Lifecycle.State
     * @return     boolean
     **/
    public boolean isAtLeast(Lifecycle.State state) {
        return lifecycleRegistry.getCurrentState().isAtLeast(state);
    }
    protected final <E extends View> E $(@NonNull View view, @IdRes int id){
        return (E)view.findViewById(id);
    }

    protected final <E extends View> E viewId(@NonNull View view,@IdRes int id){
        return (E)view.findViewById(id);
    }

}
