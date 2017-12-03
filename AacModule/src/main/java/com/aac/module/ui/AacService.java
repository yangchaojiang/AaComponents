package com.aac.module.ui;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 *
 * @see AacService
 */


public abstract class AacService<P extends AacPresenter> extends LifecycleService {
    private P t = PresenterBuilder.fromViewClass(this);
    @Override
    public void onCreate() {
        super.onCreate();
        getLifecycle().addObserver(t);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}
