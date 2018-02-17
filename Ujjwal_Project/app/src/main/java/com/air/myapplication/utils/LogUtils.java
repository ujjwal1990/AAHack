package com.air.myapplication.utils;

import android.util.Log;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class LogUtils {
    public static void ld(String from, String message) {
        if (AirApplication.logDebug) {
            Log.d(from, message);
        }
    }

    public static void e(String TAG, Exception e) {
        if (AirApplication.logDebug) {
            Log.e(TAG, "error in " + e);
            e.printStackTrace();
        }
    }

    public static void e(String TAG, String e) {
        if (AirApplication.logDebug) {
            Log.e(TAG, "error in " + e);
        }
    }

    public static void fl(String TAG, String message) {
        int maxLogSize = 4000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            LogUtils.ld("", message.substring(start, end));

        }
    }
}
