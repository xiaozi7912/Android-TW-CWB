package com.xiaozi.taiwan.cwb.model;

import org.json.JSONObject;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class RootModel {
    protected final String LOG_TAG = getClass().getSimpleName();

    public RootModel() {
    }

    public RootModel(JSONObject object) {
        if (object == null) return;
    }
}
