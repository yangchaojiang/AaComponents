package com.aac.module.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aac.module.R;
import com.aac.module.pres.PresenterBuilder;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 控制activity 控制类
 *
 * @param <P> 与activity 订阅{@link AacPresenter }类
 * @see AacActivity
 */

public abstract class AacActivity<P extends AacPresenter> extends AppCompatActivity implements LifecycleRegistryOwner {
    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private P t = PresenterBuilder.fromViewClass(this.getClass());
    //如果使用了ToolBar则自动部署。没有则无影响。
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (t != null) {
            lifecycleRegistry.addObserver(t);
            t.create(this, lifecycleRegistry);
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (t != null) {
            t.onCreateView();
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            onSetToolbar(toolbar);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void onSetToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        t.onSave(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        t.onResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
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

    @Nullable
    protected final <E extends View> E $(@NonNull View view, @IdRes int id) {
        return (E) view.findViewById(id);
    }

    @Nullable
    protected final <E extends View> E viewId(@NonNull View view, @IdRes int id) {
        return (E) view.findViewById(id);
    }

    @Nullable
    protected final <E extends View> E $(@IdRes int id) {
        return (E) findViewById(id);
    }

    @Nullable
    protected final <E extends View> E viewId(@IdRes int id) {
        return (E) findViewById(id);
    }
}
