package com.air.myapplication.utils;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class AirApplication extends Application {

    private static AirApplication mInstance;
    public static boolean logDebug = true;
    public static synchronized AirApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        mInstance = this;
    }


}
