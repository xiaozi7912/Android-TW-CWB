package com.xiaozi.taiwan.cwb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozi.taiwan.cwb.R;
import com.xiaozi.taiwan.cwb.model.CityModel;
import com.xiaozi.taiwan.cwb.model.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class Weather36ListAdapter extends RootAdapter {
    private List<WeatherModel> mDataList = null;

    public Weather36ListAdapter(Activity activity, List<WeatherModel> dataList) {
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
            convertView = mInflater.inflate(R.layout.item_weather_36_list, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WeatherModel selectedItem = mDataList.get(position);
        viewHolder.startTimeTextView.setText(String.format("%s - %s", sdf.format(selectedItem.startTime), sdf.format(selectedItem.endTime)));
        viewHolder.wxTextView.setText(selectedItem.wx.name);
        viewHolder.temperatureTextView.setText(String.format("%s ~ %s", selectedItem.minT.name, selectedItem.maxT.name));
        viewHolder.popTextView.setText(selectedItem.pop.name);
        viewHolder.ciTextView.setText(selectedItem.ci.name);
        return convertView;
    }

    private class ViewHolder {
        public TextView startTimeTextView = null;
        public TextView wxTextView = null;
        public TextView temperatureTextView = null;
        public TextView popTextView = null;
        public TextView ciTextView = null;

        public ViewHolder(View rootView) {
            startTimeTextView = rootView.findViewById(R.id.item_weather_36_start_time_text);
            wxTextView = rootView.findViewById(R.id.item_weather_36_wx_text);
            temperatureTextView = rootView.findViewById(R.id.item_weather_36_temperature_text);
            popTextView = rootView.findViewById(R.id.item_weather_36_pop_text);
            ciTextView = rootView.findViewById(R.id.item_weather_36_ci_text);
        }
    }
}
