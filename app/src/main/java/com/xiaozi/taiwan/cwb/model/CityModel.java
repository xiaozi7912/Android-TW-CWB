package com.xiaozi.taiwan.cwb.model;

import org.json.JSONObject;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class CityModel extends RootModel {
    public String name = null;
    public String dataId2 = null;
    public String dataId7 = null;

    public CityModel() {
        super();
    }

    public CityModel(JSONObject object) {
        super(object);
        this.name = object.optString("name");
        this.dataId2 = object.optJSONObject("data_id").optString("2");
        this.dataId7 = object.optJSONObject("data_id").optString("7");
    }
}
