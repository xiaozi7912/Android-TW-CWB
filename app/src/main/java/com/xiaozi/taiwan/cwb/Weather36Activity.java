package com.xiaozi.taiwan.cwb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;
import com.xiaozi.taiwan.cwb.adapter.Weather36ListAdapter;
import com.xiaozi.taiwan.cwb.dialog.SelectCityDialog;
import com.xiaozi.taiwan.cwb.manager.CWBManager;
import com.xiaozi.taiwan.cwb.model.CityModel;
import com.xiaozi.taiwan.cwb.model.Weather36Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather36Activity extends BaseActivity {
    private Button mSelectCityButton = null;
    private ListView mListView = null;

    private SelectCityDialog mSelectCityDialog = null;
    private CityModel mSelectedItem = null;

    private List<Weather36Model> mDataList = null;
    private Weather36ListAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_36);

        initView();
        initSelectCityDialog();
    }

    @Override
    protected void initView() {
        super.initView();
        mSelectCityButton = findViewById(R.id.weather_36_select_city_button);
        mListView = findViewById(R.id.weather_36_listview);

        mSelectCityButton.setOnClickListener(mOnClickListener);
    }

    private void initSelectCityDialog() {
        Logger.i(LOG_TAG, "initSelectCityDialog");
        mSelectCityDialog = SelectCityDialog.newInstance(mActivity);
        mSelectCityDialog.setCallback(new SelectCityDialog.Callback() {
            @Override
            public void onItemClick(CityModel item) {
                Logger.i(LOG_TAG, "onItemClick");
                Logger.d(LOG_TAG, "onItemClick item.parameterName : " + item.name);
                Logger.d(LOG_TAG, "onItemClick item.dataId2 : " + item.dataId2);
                Logger.d(LOG_TAG, "onItemClick item.dataId7 : " + item.dataId7);
                mSelectedItem = item;
                mSelectCityDialog.dismiss();
                updateView();
            }
        });
    }

    private void updateView() {
        Logger.i(LOG_TAG, "updateView");
        mSelectCityButton.setText(mSelectedItem.name);
        CWBManager.getInstance().getWeather36(mSelectedItem.name, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.i(LOG_TAG, "updateView onSuccess");
                Logger.d(LOG_TAG, "updateView onSuccess statusCode : " + statusCode);
                Logger.d(LOG_TAG, "updateView onSuccess headers : " + headers);
                Logger.d(LOG_TAG, "updateView onSuccess response : " + response);
                boolean success = response.optBoolean("success");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mDataList = new ArrayList<Weather36Model>();

                if (success) {
                    JSONArray weatherElements = response.optJSONObject("records").optJSONArray("location")
                            .optJSONObject(0).optJSONArray("weatherElement");
                    int elementSize = weatherElements.length();
                    int timeSize = weatherElements.optJSONObject(0).optJSONArray("time").length();
                    Logger.d(LOG_TAG, "updateView onSuccess weatherElements : " + weatherElements);
                    Logger.d(LOG_TAG, "updateView onSuccess elementSize : " + elementSize);
                    Logger.d(LOG_TAG, "updateView onSuccess timeSize : " + timeSize);

                    for (int i = 0; i < timeSize; i++) {
                        JSONObject modelObject = new JSONObject();
                        for (int j = 0; j < elementSize; j++) {
                            JSONObject elementObject = weatherElements.optJSONObject(j);
                            String elementName = elementObject.optString("elementName");
                            JSONObject timeObject = elementObject.optJSONArray("time").optJSONObject(i);

                            if (timeObject.has("startTime")) {
                                try {
                                    timeObject.put("startTime", sdf.parse(timeObject.optString("startTime")).getTime());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (timeObject.has("endTime")) {
                                try {
                                    timeObject.put("endTime", sdf.parse(timeObject.optString("endTime")).getTime());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            try {
                                modelObject.put(elementName, timeObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mDataList.add(new Weather36Model(modelObject));
                    }
                }
                mAdapter = new Weather36ListAdapter(mActivity, mDataList);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Logger.i(LOG_TAG, "updateView onFailure");
                Logger.d(LOG_TAG, "updateView onFailure statusCode : " + statusCode);
                Logger.d(LOG_TAG, "updateView onFailure headers : " + headers);
                Logger.d(LOG_TAG, "updateView onFailure throwable : " + throwable);
                Logger.d(LOG_TAG, "updateView onFailure errorResponse : " + errorResponse);
            }
        });
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSelectCityDialog.show();
        }
    };
}
