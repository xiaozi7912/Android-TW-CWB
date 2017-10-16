package com.xiaozi.taiwan.cwb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;

public class MainActivity extends BaseActivity {
    private Button mShowWeather36Button = null;
    private Button mShowWeather2DaysButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.init(BuildConfig.DEBUG);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        mShowWeather36Button = findViewById(R.id.main_show_weather_36_button);
        mShowWeather2DaysButton = findViewById(R.id.main_show_weather_2days_button);

        mShowWeather36Button.setOnClickListener(mOnClickListener);
        mShowWeather2DaysButton.setOnClickListener(mOnClickListener);
    }

    private void onShowWeather36ButtonClick() {
        Logger.i(LOG_TAG, "onShowWeather36ButtonClick");
        Intent intent = new Intent(mActivity, Weather36Activity.class);
        startActivity(intent);
    }

    private void onShowWeather2DaysButtonClick() {
        Logger.i(LOG_TAG, "onShowWeather2DaysButtonClick");
        Intent intent = new Intent(mActivity, Weather2DaysActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_show_weather_36_button:
                    onShowWeather36ButtonClick();
                    break;
                case R.id.main_show_weather_2days_button:
                    onShowWeather2DaysButtonClick();
                    break;
            }
        }
    };
}
