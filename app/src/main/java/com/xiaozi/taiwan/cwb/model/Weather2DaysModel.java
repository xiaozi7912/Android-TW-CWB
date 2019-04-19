package com.xiaozi.taiwan.cwb.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather2DaysModel extends RootModel {
    private List<ElementT> mElementTList = null;
    public ElementT elementT = null;
    public ElementWind elementWind = null;
    public ElementCI elementCI = null;
    public ElementWx elementWx = null;
    public ElementPop elementPop = null;
    public ElementWeatherDesc elementWeatherDesc = null;

    public Weather2DaysModel() {
        mElementTList = new ArrayList<>();
    }

    public Weather2DaysModel(JSONObject object) {
        super(object);
        this.elementT = new ElementT(object.optJSONObject("T"));
//        this.elementWind = new ElementWind(object.optJSONObject("Wind"));
        this.elementCI = new ElementCI(object.optJSONObject("CI"));
        this.elementWx = new ElementWx(object.optJSONObject("Wx"));
//        this.elementPop = new ElementPop(object.optJSONObject("PoP"));
        this.elementWeatherDesc = new ElementWeatherDesc(object.optJSONObject("WeatherDescription"));
    }

    public void addElementT(JSONObject object) {
        mElementTList.add(new ElementT(object));
    }

    public ElementT getElementT(int position) {
        return mElementTList.get(position);
    }

    public class ElementT extends RootModel {
        public String dataTime = null;
        public List<Map<String, String>> elementValueList = null;

        public ElementT(JSONObject object) {
            super(object);
            JSONArray elementValue = object.optJSONArray("elementValue");
            int elementValueSize = elementValue.length();

            this.dataTime = object.optString("dataTime");
            this.elementValueList = new ArrayList<Map<String, String>>();
            for (int i = 0; i < elementValueSize; i++) {
                JSONObject jsonObject = elementValue.optJSONObject(i);
                HashMap<String, String> elementValueItem = new HashMap<>();
                elementValueItem.put("value", jsonObject.optString("value"));
                elementValueItem.put("measures", jsonObject.optString("measures"));
                elementValueList.add(elementValueItem);
            }
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
            this.parameterName = object.optJSONArray("elementValue").optJSONObject(0).optString("measures");
            this.parameterValue = object.optJSONArray("elementValue").optJSONObject(0).optString("value");
        }
    }

    public class ElementWx extends RootModel {
        public long startTime = 0;
        public long endTime = 0;
        public String elementValue = null;
        public String parameterName = null;
        public String parameterValue = null;
        public List<Map<String, String>> dataList = null;

        public ElementWx(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.elementValue = object.optString("elementValue");
            this.dataList = new ArrayList<Map<String, String>>();

            JSONArray jsonArray = object.optJSONArray("elementValue");
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, String> item = new HashMap<>();

                item.put("value", jsonArray.optJSONObject(i).optString("value"));
                item.put("measures", jsonArray.optJSONObject(i).optString("measures"));
                dataList.add(item);
            }
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
        public String parameterName = null;
        public String parameterValue = null;

        public ElementWeatherDesc(JSONObject object) {
            super(object);
            this.startTime = object.optLong("startTime");
            this.endTime = object.optLong("endTime");
            this.elementValue = object.optString("elementValue");
            this.parameterName = object.optJSONArray("elementValue").optJSONObject(0).optString("measures");
            this.parameterValue = object.optJSONArray("elementValue").optJSONObject(0).optString("value");
        }
    }
}
