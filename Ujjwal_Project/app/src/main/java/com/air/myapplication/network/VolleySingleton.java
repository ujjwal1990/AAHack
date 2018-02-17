package com.air.myapplication.network;

import com.air.myapplication.utils.AirApplication;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    //    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    int MAX_SERIAL_THREAD_POOL_SIZE = 1;
    final int MAX_CACHE_SIZE = 2 * 1024 * 1024; //2 MB

    private VolleySingleton() {
        Cache cache = new DiskBasedCache(AirApplication.getAppContext().getCacheDir(), MAX_CACHE_SIZE);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network, MAX_SERIAL_THREAD_POOL_SIZE);
        mRequestQueue.start();
    }

    public static VolleySingleton getInstance() {
        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}