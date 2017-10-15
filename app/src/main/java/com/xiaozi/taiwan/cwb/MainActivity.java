package com.xiaozi.taiwan.cwb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;

public class MainActivity extends BaseActivity {
    private Button mShowWeather36Button = null;

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

        mShowWeather36Button.setOnClickListener(mOnClickListener);
    }

    private void onShowWeather36ButtonClick() {
        Logger.i(LOG_TAG, "onShowWeather36ButtonClick");
        Intent intent = new Intent(mActivity, Weather36Activity.class);
        startActivity(intent);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_show_weather_36_button:
                    onShowWeather36ButtonClick();
                    break;
            }
        }
    };
}
