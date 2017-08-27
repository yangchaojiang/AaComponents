package com.example.aac.model;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yangc on 2017/8/16.
 */

public class Testssssssssssssssssss implements Application.ActivityLifecycleCallbacks {

    private String data;
    private  String TAG="Testssssssssssssssssss";

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Log.d(TAG,"onActivityCreated");

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG,"onActivityStarted");

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG,"onActivityResumed");

    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG,"onActivityPaused");

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG,"onActivityCreated");

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Log.d(TAG,"onActivitySaveInstanceState");

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG,"onActivityDestroyed");

    }
}
