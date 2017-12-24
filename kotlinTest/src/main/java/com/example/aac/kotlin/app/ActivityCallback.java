package com.example.aac.kotlin.app;

import android.app.Activity;
import android.os.Bundle;

import com.aac.module.callback.ActivityLifecycleCallbacksWrapper;

/**
 * Created by yangc  on 2017/8/17.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
public class ActivityCallback extends ActivityLifecycleCallbacksWrapper {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
    }
}
