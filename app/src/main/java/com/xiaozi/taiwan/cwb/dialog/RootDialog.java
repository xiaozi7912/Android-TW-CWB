package com.xiaozi.taiwan.cwb.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class RootDialog {
    protected final String LOG_TAG = getClass().getSimpleName();
    protected Activity mActivity = null;
    protected LayoutInflater mInflater = null;
    protected AlertDialog mAlertDialog = null;

    protected View mRootView = null;

    public RootDialog(Activity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mAlertDialog = new AlertDialog.Builder(activity).create();
    }

    protected void initView(int layoutId) {
        mRootView = mInflater.inflate(layoutId, null, false);
    }

    public void show() {
        mAlertDialog.show();
        mAlertDialog.setContentView(mRootView);
    }

    public void dismiss() {
        mAlertDialog.dismiss();
    }
}
