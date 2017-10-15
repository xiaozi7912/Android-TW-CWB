package com.xiaozi.taiwan.cwb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class RootAdapter extends BaseAdapter {
    protected final String LOG_TAG = getClass().getSimpleName();
    protected Activity mActivity = null;
    protected LayoutInflater mInflater = null;

    public RootAdapter(Activity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
