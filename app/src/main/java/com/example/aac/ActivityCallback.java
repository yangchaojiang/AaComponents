package com.example.aac;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.aac.module.callback.ActivityLifecycleCallbacksWrapper;

/**
 * Created by yangc  on 2017/8/17.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 周期回调
 */
public class ActivityCallback extends ActivityLifecycleCallbacksWrapper {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(false);
        Resources res = activity.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
    }
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        super.onActivitySaveInstanceState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
    }

}
