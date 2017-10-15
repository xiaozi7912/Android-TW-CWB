package com.xiaozi.taiwan.cwb.dialog;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaozi.framework.libs.utils.Logger;
import com.xiaozi.taiwan.cwb.R;
import com.xiaozi.taiwan.cwb.adapter.SelectCityListAdapter;
import com.xiaozi.taiwan.cwb.model.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class SelectCityDialog extends RootDialog {
    private ListView mListView = null;

    private Callback mCallback = null;
    private List<CityModel> mDataList = null;
    private SelectCityListAdapter mAdapter = null;
    private CityModel mSelectedItem = null;

    private final static String CITY_FILE_NAME = "taiwan_cities.json";

    public SelectCityDialog(Activity activity) {
        super(activity);

        initView(R.layout.view_select_city);
        initDataList();
        initListView();
    }

    @Override
    protected void initView(int layoutId) {
        super.initView(layoutId);
        mListView = mRootView.findViewById(R.id.view_select_city_listview);

        mListView.setOnItemClickListener(mOnItemClickListener);
    }

    private void initDataList() {
        Logger.i(LOG_TAG, "initDataList");
        AssetManager manager = mActivity.getAssets();
        InputStream inputStream = null;
        mDataList = new ArrayList<CityModel>();

        try {
            inputStream = manager.open(CITY_FILE_NAME);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int readSize = 0;

            while ((readSize = inputStream.read(buffer)) > 0) {
                baos.write(buffer, 0, readSize);
            }

            JSONArray jsonArray = new JSONArray(baos.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                mDataList.add(new CityModel(jsonArray.optJSONObject(i)));
            }

            baos.flush();
            baos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initListView() {
        Logger.i(LOG_TAG, "initListView");
        mAdapter = new SelectCityListAdapter(mActivity, mDataList);
        mListView.setAdapter(mAdapter);
    }

    public static SelectCityDialog newInstance(Activity activity) {
        return new SelectCityDialog(activity);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Logger.i(LOG_TAG, "onItemClick");
            mSelectedItem = mDataList.get(position);
            Logger.d(LOG_TAG, "onItemClick mSelectedItem.name : " + mSelectedItem.name);
            Logger.d(LOG_TAG, "onItemClick mSelectedItem.dataId2 : " + mSelectedItem.dataId2);
            Logger.d(LOG_TAG, "onItemClick mSelectedItem.dataId7 : " + mSelectedItem.dataId7);
            if (mCallback != null) mCallback.onItemClick(mSelectedItem);
        }
    };

    public interface Callback {
        void onItemClick(CityModel item);
    }
}
