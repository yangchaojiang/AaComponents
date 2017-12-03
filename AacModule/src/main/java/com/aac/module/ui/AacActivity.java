package com.aac.module.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aac.module.R;
import com.aac.module.utils.ContentLayoutListener;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 控制activity 控制类
 *
 * @param <P> 与activity 订阅{@link AacPresenter }类
 * @see AacActivity
 */

public abstract class AacActivity<P extends AacPresenter> extends AppCompatActivity implements  ContentLayoutListener {
    private  LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private P t = PresenterBuilder.fromViewClass(this);

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentLayoutId() == 0) {
            throw new NullPointerException(getString(R.string.aac_layout_error_hint));
        }
        setContentView(getContentLayoutId());
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            onSetToolbar(toolbar);
        }
        if (t != null) {
            lifecycleRegistry.addObserver(t);
            t.onCreateView();
        }

    }
    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleRegistry.removeObserver(t);
        lifecycleRegistry = null;
        t = null;
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        t.onSave(outState);
    }

    @NonNull
    @CallSuper
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @CallSuper
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        t.onResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 设置Toolbar如果使用了ToolBar则自动部署。没有则无影响。
     *
     * @param toolbar toolbar
     **/
    public void onSetToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener((view) -> finish());
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
    /***
     * 得到对应业务处理类
     *
     * @return L
     **/
    public P getPresenter() {
        return t;
    }
    /**
     * 返回当前生命中周期状态
     *
     * @param state Lifecycle.State
     * @return boolean
     **/
    public boolean getState(Lifecycle.State state) {
        return lifecycleRegistry.getCurrentState().isAtLeast(state);
    }

}
