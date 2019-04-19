package com.xiaozi.taiwan.cwb;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;
import com.xiaozi.taiwan.cwb.dialog.SelectCityDialog;
import com.xiaozi.taiwan.cwb.manager.CWBManager;
import com.xiaozi.taiwan.cwb.model.CityModel;
import com.xiaozi.taiwan.cwb.model.Weather2DaysModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import cz.msebera.android.httpclient.Header;

/**
 * Created by user on 2017-10-16.
 */

public class Weather2DaysActivity extends BaseActivity {
    private Button mSelectCityButton = null;
    private RecyclerView mPOP12HRecyclerView = null;
    private RecyclerView mATRecyclerView = null;
    private RecyclerView mTRecyclerView = null;

    private SelectCityDialog mSelectCityDialog = null;
    private CityModel mSelectedItem = null;

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
        mPOP12HRecyclerView = findViewById(R.id.weather_2days_pop12h_list);
        mATRecyclerView = findViewById(R.id.weather_2days_at_list);
        mTRecyclerView = findViewById(R.id.weather_2days_t_list);

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

                if (success) {
                    Weather2DaysModel model = new Weather2DaysModel();
                    JSONArray weatherElements = response.optJSONObject("records").optJSONArray("locations")
                            .optJSONObject(0).optJSONArray("location")
                            .optJSONObject(0).optJSONArray("weatherElement");
                    int elementSize = weatherElements.length();
                    Logger.d(LOG_TAG, "updateView onSuccess weatherElements : " + weatherElements);
                    Logger.d(LOG_TAG, "updateView onSuccess elementSize : " + elementSize);

                    for (int i = 0; i < elementSize; i++) {
                        JSONObject elementObject = weatherElements.optJSONObject(i);
                        String elementName = elementObject.optString("elementName");
                        String description = elementObject.optString("description");
                        JSONArray time = elementObject.optJSONArray("time");
                        int timeSize = time.length();

                        Logger.d(LOG_TAG, "updateView onSuccess elementName : " + elementName);
                        Logger.d(LOG_TAG, "updateView onSuccess description : " + description);
                        Logger.d(LOG_TAG, "updateView onSuccess time.length : " + time.length());

                        for (int j = 0; j < timeSize; j++) {
                            JSONObject timeObject = time.optJSONObject(j);
                            Logger.d(LOG_TAG, "updateView onSuccess timeObject : " + timeObject);

                            if (elementName.equals("T")) {
                                model.addElementT(timeObject);
                            } else if (elementName.equals("CI")) {

                            } else if (elementName.equals("PoP6h")) {

                            }
                        }
                    }
                }
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
