package com.xiaozi.taiwan.cwb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;
import com.xiaozi.taiwan.cwb.adapter.Weather2DaysListAdapter;
import com.xiaozi.taiwan.cwb.dialog.SelectCityDialog;
import com.xiaozi.taiwan.cwb.manager.CWBManager;
import com.xiaozi.taiwan.cwb.model.CityModel;
import com.xiaozi.taiwan.cwb.model.Weather2DaysModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 2017-10-16.
 */

public class Weather2DaysActivity extends BaseActivity {
    private Button mSelectCityButton = null;
    private ListView mListView = null;

    private SelectCityDialog mSelectCityDialog = null;
    private CityModel mSelectedItem = null;

    private List<Weather2DaysModel> mDataList = null;
    private Weather2DaysListAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_2days);

        initView();
        initSelectCityDialog();
    }

    @Override
    protected void initView() {
        super.initView();
        mSelectCityButton = findViewById(R.id.weather_2days_select_city_button);
        mListView = findViewById(R.id.weather_2days_listview);

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
        CWBManager.getInstance().getWeather2Days(mSelectedItem.name, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Logger.i(LOG_TAG, "updateView onSuccess");
                Logger.d(LOG_TAG, "updateView onSuccess statusCode : " + statusCode);
                Logger.d(LOG_TAG, "updateView onSuccess headers : " + headers);
                Logger.d(LOG_TAG, "updateView onSuccess response : " + response);
                boolean success = response.optBoolean("success");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mDataList = new ArrayList<Weather2DaysModel>();

                if (success) {
                    JSONArray weatherElements = response.optJSONObject("records").optJSONArray("locations")
                            .optJSONObject(0).optJSONArray("location")
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
                            Logger.d(LOG_TAG, "updateView onSuccess i : " + i);
                            Logger.d(LOG_TAG, "updateView onSuccess j : " + j);
                            Logger.d(LOG_TAG, "updateView onSuccess elementName : " + elementName);

                            if (timeObject != null) {
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

                                if (timeObject.has("dataTime")) {
                                    try {
                                        timeObject.put("dataTime", sdf.parse(timeObject.optString("dataTime")).getTime());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            try {
                                modelObject.put(elementName, timeObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mDataList.add(new Weather2DaysModel(modelObject));
                    }
                }
                mAdapter = new Weather2DaysListAdapter(mActivity, mDataList);
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
