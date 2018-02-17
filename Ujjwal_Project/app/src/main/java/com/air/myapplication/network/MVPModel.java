package com.air.myapplication.network;

import android.support.v4.util.ArrayMap;

public interface MVPModel {

    interface NetworkServiceModel {
        void getBookingHistory(ArrayMap<String, String> params, ApiResponseHandler apiResponseHandler);
    }
}
