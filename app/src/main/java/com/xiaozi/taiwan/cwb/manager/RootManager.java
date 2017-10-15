package com.xiaozi.taiwan.cwb.manager;

import android.content.Context;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class RootManager {
    protected final String LOG_TAG = getClass().getSimpleName();
    protected Context mContext = null;

    public RootManager(Context context) {
        mContext = context;
    }
}
