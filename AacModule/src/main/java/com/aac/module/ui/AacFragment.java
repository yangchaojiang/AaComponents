package com.aac.module.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aac.module.R;
import com.aac.module.utils.ContentLayoutListener;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 *
 * @param <P> AacFragment 订阅{@link AacFragmentPresenter }类
 * @see AacFragment
 */

public abstract class AacFragment<P extends AacFragmentPresenter> extends Fragment implements ContentLayoutListener {
    private   LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private P t = PresenterBuilder.fromViewClass(this);
    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleRegistry.addObserver(t);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentLayoutId() == 0) {
            throw new NullPointerException(getString(R.string.aac_layout_error_hint));
        }
        return inflater.inflate(getContentLayoutId(), container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        t.onCreateView();
        super.onViewCreated(view, savedInstanceState);
        t.onViewCreated();
    }
    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }
    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
       lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        t.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED);
        lifecycleRegistry.removeObserver(t);
        lifecycleRegistry = null;
        t = null;
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    /***
     * 得到对应业务处理类
     *
     * @return R
     **/
    public P getPresenter() {
        return t;
    }

    /**
     * 返回是否当前生命中周期状态
     *
     * @param state Lifecycle.State
     * @return boolean
     **/
    public boolean getState(Lifecycle.State state) {
        return lifecycleRegistry.getCurrentState().isAtLeast(state);
    }

}
