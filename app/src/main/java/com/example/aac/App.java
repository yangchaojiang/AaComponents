package com.example.aac;

import android.app.Application;

/**
 * Created by yangc  on 2017/8/17.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityCallback());
    }
}
