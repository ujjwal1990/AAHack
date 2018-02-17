package com.air.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class GeneralUtil {
    public static boolean isNetworkConnectionAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) return false;
            NetworkInfo.State network = info.getState();

            if (network == NetworkInfo.State.CONNECTED) {
                return true;
            } else if (network == NetworkInfo.State.CONNECTING) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
