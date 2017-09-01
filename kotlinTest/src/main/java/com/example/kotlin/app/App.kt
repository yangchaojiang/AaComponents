package com.example.kotlin.app

import android.app.Application

/**
 * Created by yangc on 2017/9/1.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

class App :Application(){

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityCallback())
    }

}