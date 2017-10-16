package com.xiaozi.taiwan.cwb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozi.taiwan.cwb.R;
import com.xiaozi.taiwan.cwb.model.Weather2DaysModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather2DaysListAdapter extends RootAdapter {
    private List<Weather2DaysModel> mDataList = null;

    public Weather2DaysListAdapter(Activity activity, List<Weather2DaysModel> dataList) {
        super(activity);
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_weather_2days_list, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Weather2DaysModel selectedItem = mDataList.get(position);
        viewHolder.startTimeTextView.setText(String.format("%s - %s",
                sdf.format(selectedItem.elementWx.startTime),
                sdf.format(selectedItem.elementWx.endTime)));
        viewHolder.wxTextView.setText(selectedItem.elementWx.elementValue);
        viewHolder.temperatureTextView.setText(String.format("%s ~ %s",
                selectedItem.elementT.elementValue,
                selectedItem.elementT.elementValue));
//        viewHolder.popTextView.setText(selectedItem.elementPop.elementValue);
        viewHolder.ciTextView.setText(selectedItem.elementCI.elementValue);
        viewHolder.weatherDescriptionTextView.setText(selectedItem.elementWeatherDesc.elementValue);
        return convertView;
    }

    private class ViewHolder {
        public TextView startTimeTextView = null;
        public TextView wxTextView = null;
        public TextView temperatureTextView = null;
        public TextView popTextView = null;
        public TextView ciTextView = null;
        public TextView weatherDescriptionTextView = null;

        public ViewHolder(View rootView) {
            startTimeTextView = rootView.findViewById(R.id.item_weather_36_start_time_text);
            wxTextView = rootView.findViewById(R.id.item_weather_36_wx_text);
            temperatureTextView = rootView.findViewById(R.id.item_weather_36_temperature_text);
            popTextView = rootView.findViewById(R.id.item_weather_36_pop_text);
            ciTextView = rootView.findViewById(R.id.item_weather_36_ci_text);
            weatherDescriptionTextView = rootView.findViewById(R.id.item_weather_2days_weather_description_text);
        }
    }
}
