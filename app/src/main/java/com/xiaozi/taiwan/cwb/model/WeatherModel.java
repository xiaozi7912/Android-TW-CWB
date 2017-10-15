package com.xiaozi.taiwan.cwb.model;

import org.json.JSONObject;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class WeatherModel extends RootModel {
    public long startTime = 0;
    public long endTime = 0;
    public Wx wx = null;
    public Temperature maxT = null;
    public Temperature minT = null;
    public Pop pop = null;
    public CI ci = null;

    public void setStartTime(long time) {
        startTime = time;
    }

    public void setEndTime(long time) {
        endTime = time;
    }

    public void setWx(JSONObject object) {
        this.wx = new Wx(object);
    }

    public void setMaxT(JSONObject object) {
        this.maxT = new Temperature(object);
    }

    public void setMinT(JSONObject object) {
        this.minT = new Temperature(object);
    }

    public void setPop(JSONObject object) {
        this.pop = new Pop(object);
    }

    public void setCI(JSONObject object){
        this.ci = new CI(object);
    }

    public class Wx extends RootModel {
        public String name = null;
        public String value = null;

        public Wx(JSONObject object) {
            super(object);
            this.name = object.optString("parameterName");
            this.value = object.optString("parameterValue");
        }
    }

    public class Temperature extends RootModel {
        public String name = null;
        public String unit = null;

        public Temperature(JSONObject object) {
            super(object);
            this.name = object.optString("parameterName");
            this.unit = object.optString("parameterUnit");
        }
    }

    public class Pop extends RootModel {
        public String name = null;
        public String unit = null;

        public Pop(JSONObject object) {
            super(object);
            this.name = object.optString("parameterName");
            this.unit = object.optString("parameterUnit");
        }
    }

    public class CI extends RootModel {
        public String name = null;

        public CI(JSONObject object) {
            super(object);
            this.name = object.optString("parameterName");
        }
    }
}
