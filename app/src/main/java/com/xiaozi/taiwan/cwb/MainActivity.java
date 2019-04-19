package com.xiaozi.taiwan.cwb;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.xiaozi.framework.libs.BaseActivity;
import com.xiaozi.framework.libs.utils.Logger;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {
    private SupportMapFragment mMapFragment = null;
    private GoogleMap mGoogleMap = null;

    private FusedLocationProviderClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.init(BuildConfig.DEBUG);

        mLocationClient = LocationServices.getFusedLocationProviderClient(mActivity);

        requestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        super.initView();
        mMapFragment = SupportMapFragment.newInstance();
        mMapFragment.getMapAsync(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_frame_layout, mMapFragment).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        updateLocationUI();
        getDeviceLocation();
    }

    @AfterPermissionGranted(REQUEST_PERMISSION_CODE)
    public void requestPermission() {
        String[] perms = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

        if (EasyPermissions.hasPermissions(mActivity, perms)) {
            initView();
        } else {
            EasyPermissions.requestPermissions(mActivity, "Request Permission", REQUEST_PERMISSION_CODE, perms);
        }
    }

    private void updateLocationUI() {
        Logger.i(LOG_TAG, "updateLocationUI");
        if (mGoogleMap == null) return;

        try {
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getDeviceLocation() {
        Logger.i(LOG_TAG, "getDeviceLocation");
        try {
            Task locationResult = mLocationClient.getLastLocation();
            locationResult.addOnCompleteListener(mActivity, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location lastKnownLocation = (Location) task.getResult();
                        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(lastKnownLocation.getLatitude(),
                                        lastKnownLocation.getLongitude()), 14));
                        mGoogleMap.addMarker(new MarkerOptions()
                                .title("基隆市")
                                .position(new LatLng(25.153191, 121.759355))
                                .snippet("123456")).showInfoWindow();
                    }
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
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

            }
        }
    };
}
