package com.air.myapplication.network;

public interface ApiResponseHandler {
    void onSuccess(String requestUrl, ApiRequestId apiRequestId, String response);

    void onFailure(int requestCode, String requestUrl, ApiRequestId apiRequestId, String response);
}
