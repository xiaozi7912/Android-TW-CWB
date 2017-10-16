package com.xiaozi.taiwan.cwb.model;

import org.json.JSONObject;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather2DaysModel extends RootModel {
    public ElementT elementT = null;
    public ElementWind elementWind = null;
    public ElementCI elementCI = null;
    public ElementWx elementWx = null;
    public ElementPop elementPop = null;
    public ElementWeatherDesc elementWeatherDesc = null;

    public Weather2DaysModel(JSONObject object) {
        super(object);
        this.elementT = new ElementT(object.optJSONObject("T"));
//        this.elementWind = new ElementWind(object.optJSONObject("Wind"));
        this.elementCI = new ElementCI(object.optJSONObject("CI"));
        this.elementWx = new ElementWx(object.optJSONObject("Wx"));
//        this.elementPop = new ElementPop(object.optJSONObject("PoP"));
        this.elementWeatherDesc = new ElementWeatherDesc(object.optJSONObject("WeatherDescription"));
    }

    public class ElementT extends RootModel {
        public long dataTime = 0;
        public String elementValue = null;

        public ElementT(JSONObject object) {
            super(object);
            this.dataTime = object.optLong("dataTime");
            this.elementValue = object.optString("elementValue");
        }
    }

    public class ElementWind extends RootModel {
        public long dataTime = 0;
        public String parameterName = null;
        public String parameterValue = null;
        public String parameterUnit = null;

        public ElementWind(JSONObject object) {
            super(object);
            this.dataTime = object.optLong("dataTime");
            this.parameterName = object.optJSONObject("parameter").optString("parameterName");
            this.parameterValue = object.optJSONObject("parameter").optString("parameterValue");
            this.parameterUnit = object.optJSONObject("parameter").optString("parameterUnit");
        }
    }

    public class ElementCI extends RootModel {
        public long dataTime = 0;
        public String elementValue = null;
        public String parameterName = null;
        public String parameterValue = null;

        public ElementCI(JSONObject object) {
            super(object);
            this.dataTime = object.optLong("dataTime");
            this.elementValue = object.optString("elementValue");
            this.parameterName = object.optJSONArray("parameter").optJSONObject(0).optString("parameterName");
            this.parameterValue = object.optJSONArray("parameter").optJSONObject(0).optString("parameterValue");
        }
    }

    public class ElementWx extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String elementValue = null;
        public String parameterName = null;
        public String parameterValue = null;

        public ElementWx(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.elementValue = object.optString("elementValue");
            this.parameterName = object.optJSONArray("parameter").optJSONObject(0).optString("parameterName");
            this.parameterValue = object.optJSONArray("parameter").optJSONObject(0).optString("parameterValue");
        }
    }

    public class ElementPop extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String elementValue = null;

        public ElementPop(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.elementValue = object.optString("elementValue");
        }
    }

    public class ElementWeatherDesc extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String elementValue = null;

        public ElementWeatherDesc(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.elementValue = object.optString("elementValue");
        }
    }
}
