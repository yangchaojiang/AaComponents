package com.example.aac.java.service.ui;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.aac.module.ui.AacService;
import com.example.aac.java.service.presenter.MyRresenter;

/**
 * Created by yangc on 2017/9/19.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class MYService extends AacService<MyRresenter> {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
