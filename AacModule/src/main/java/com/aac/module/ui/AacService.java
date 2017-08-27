package com.aac.module.ui;

import android.arch.lifecycle.LifecycleService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.aac.module.pres.PresenterBuilder;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 控制activity 控制类
 */

public abstract class AacService<LifecycleObserverType extends AacServicePresenter> extends LifecycleService {

    private LifecycleObserverType t = PresenterBuilder.fromViewClass(this.getClass());

    @Override
    public void onCreate() {
        super.onCreate();
        getLifecycle().addObserver(t);
        t.create(this, getLifecycle());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        t.onStartCommand(intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        t.onBind(intent);
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        t.onUnbind(intent);
        return super.onUnbind(intent);
    }
}
