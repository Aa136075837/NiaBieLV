package com.example.bo.niabielv.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.bo.niabielv.app.NiaBieApplication;


/**
 * @author zsp
 * @date 2016/9/9 0009
 * @description
 */
public class NetworkUtils {
    public static final int NET_TYPE_WIFI = 0x01;
    public static final int NO_NETWORK = 0;
    public static final int NET_TYPE_CMWAP = 0x02;
    public static final int NET_TYPE_CMNET = 0x03;

    public static int getNetworkType() {
        int netType = NO_NETWORK;
        ConnectivityManager connectivityManager = (ConnectivityManager) NiaBieApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NET_TYPE_CMNET;
                } else {
                    netType = NET_TYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NET_TYPE_WIFI;
        }
        return netType;
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) NiaBieApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
}
