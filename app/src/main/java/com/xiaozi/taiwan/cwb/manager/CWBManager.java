package com.xiaozi.taiwan.cwb.manager;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.xiaozi.framework.libs.utils.Logger;
import com.xiaozi.taiwan.cwb.BuildConfig;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Created by xiaoz on 2017-10-16.
 */

public class CWBManager extends RootManager {
    private final static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
    private static CWBManager mInstance = null;

    private final static String WEATHER_36_DATA_ID = "F-C0032-001";
    private final static String WEATHER_2DAYS_DATA_ID = "F-D0047-089";

    public CWBManager(Context context) {
        super(context);

        // javax.net.ssl.SSLException: hostname in certificate didn't match
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
            socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            mAsyncHttpClient.setSSLSocketFactory(socketFactory);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        mAsyncHttpClient.addHeader("Authorization", BuildConfig.API_KEY_CWB);
    }

    public static void init(Context context) {
        mInstance = new CWBManager(context);
    }

    public static CWBManager getInstance() {
        return mInstance;
    }

    public void getWeather36(String city, JsonHttpResponseHandler handler) {
        Logger.i(LOG_TAG, "getWeather36");
        Logger.d(LOG_TAG, "getWeather36 city : " + city);
        String url = String.format("%s/%s", BuildConfig.API_ROOT_CWB, WEATHER_36_DATA_ID);
        RequestParams params = new RequestParams();
        params.put("locationName", city);
        params.put("sort", "time");
        mAsyncHttpClient.get(url, params, handler);
    }

    public void getWeather2Days(String city, JsonHttpResponseHandler handler) {
        Logger.i(LOG_TAG, "getWeather2Days");
        Logger.d(LOG_TAG, "getWeather2Days city : " + city);
        String url = String.format("%s/%s", BuildConfig.API_ROOT_CWB, WEATHER_2DAYS_DATA_ID);
        RequestParams params = new RequestParams();
        params.put("locationName", city);
        params.put("sort", "time");
        mAsyncHttpClient.get(url, params, handler);
    }
}
