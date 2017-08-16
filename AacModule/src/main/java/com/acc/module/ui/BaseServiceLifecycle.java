package com.acc.module.ui;

import android.content.Intent;

/**
 * Created by yangc on 2017/8/13.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:  service业务处理控制类
 */

public  abstract class BaseServiceLifecycle<ActivityType> extends BaseLifecycle<ActivityType> {


    protected void onStartCommand(Intent intent, int flags, int startId) {
    }

    protected void onBind(Intent intent) {
    }

    protected void onUnbind(Intent intent) {

    }

}
