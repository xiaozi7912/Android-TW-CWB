package com.xiaozi.taiwan.cwb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaozi.taiwan.cwb.R;
import com.xiaozi.taiwan.cwb.model.CityModel;

import java.util.List;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class SelectCityListAdapter extends RootAdapter {
    private List<CityModel> mDataList = null;

    public SelectCityListAdapter(Activity activity, List<CityModel> dataList) {
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
            convertView = mInflater.inflate(R.layout.item_select_city_list, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CityModel selectedItem = mDataList.get(position);
        viewHolder.nameTextView.setText(selectedItem.name);
        viewHolder.dataId2TextView.setText(selectedItem.dataId2);
        viewHolder.dataId7TextView.setText(selectedItem.dataId7);
        return convertView;
    }

    private class ViewHolder {
        public TextView nameTextView = null;
        public TextView dataId2TextView = null;
        public TextView dataId7TextView = null;

        public ViewHolder(View rootView) {
            nameTextView = rootView.findViewById(R.id.item_select_city_list_name_text);
            dataId2TextView = rootView.findViewById(R.id.item_select_city_list_data_id_2_text);
            dataId7TextView = rootView.findViewById(R.id.item_select_clity_list_data_id_7_text);
        }
    }
}
