package com.xiaozi.taiwan.cwb.model;

import org.json.JSONObject;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather36Model extends RootModel {
    public ElementWx elementWx = null;
    public ElementPop elementPop = null;
    public ElementT elementMinT = null;
    public ElementCI elementCI = null;
    public ElementT elementMaxT = null;

    public Weather36Model(JSONObject object) {
        super(object);
        this.elementWx = new ElementWx(object.optJSONObject("Wx"));
        this.elementPop = new ElementPop(object.optJSONObject("PoP"));
        this.elementMinT = new ElementT(object.optJSONObject("MinT"));
        this.elementCI = new ElementCI(object.optJSONObject("CI"));
        this.elementMaxT = new ElementT(object.optJSONObject("MaxT"));
    }

    public class ElementWx extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String parameterName = null;
        public String parameterValue = null;

        public ElementWx(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.parameterName = object.optJSONObject("parameter").optString("parameterName");
            this.parameterValue = object.optJSONObject("parameter").optString("parameterValue");
        }
    }

    public class ElementT extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String parameterName = null;
        public String parameterUnit = null;

        public ElementT(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.parameterName = object.optJSONObject("parameter").optString("parameterName");
            this.parameterUnit = object.optJSONObject("parameter").optString("parameterUnit");
        }
    }

    public class ElementPop extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String parameterName = null;
        public String parameterUnit = null;

        public ElementPop(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.parameterName = object.optJSONObject("parameter").optString("parameterName");
            this.parameterUnit = object.optJSONObject("parameter").optString("parameterUnit");
        }
    }

    public class ElementCI extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String parameterName = null;

        public ElementCI(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.parameterName = object.optJSONObject("parameter").optString("parameterName");
        }
    }
}
