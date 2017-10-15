package com.xiaozi.taiwan.cwb.utils;

import android.app.Application;

import com.xiaozi.taiwan.cwb.manager.CWBManager;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class RootApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CWBManager.init(getApplicationContext());
    }
}
