package com.aac.module.ui;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.aac.module.pres.PresenterBuilder;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 *
 * @see AacService
 */


public abstract class AacService<P extends AacPresenter> extends LifecycleService {

    private P t = PresenterBuilder.fromViewClass(this.getClass());

    @Override
    public void onCreate() {
        super.onCreate();
        getLifecycle().addObserver(t);
        t.create(this, getLifecycle());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}
