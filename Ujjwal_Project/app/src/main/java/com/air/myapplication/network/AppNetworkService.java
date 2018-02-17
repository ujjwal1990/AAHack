package com.air.myapplication.network;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.widget.Toast;

import com.air.myapplication.utils.ApiServiceUtil;
import com.air.myapplication.utils.AppConstants;
import com.air.myapplication.utils.GeneralUtil;
import com.air.myapplication.utils.LogUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Date;

public class AppNetworkService implements MVPModel.NetworkServiceModel {

    private static AppNetworkService mInstance = null;
    StringRequest stringRequest;
    ApiResponseHandler apiResponseHandler;
    ApiRequestId requestIdPrev;
    private Context context;
    private String TAG = AppNetworkService.class.getSimpleName();
    private String errorMsg;
    private boolean firstErrorMsg;
    private JsonObjectRequest jsonRequest;
    private int VOLLEY_NETWORK_CALL_RETRY = 2;
    private int VOLLEY_NETWORK_CALL_TIMEOUT_MS = 3500;  //timeout in milliseconds
    private long calltime1, calltime2;


    public AppNetworkService(Context context) {
        this.context = context;
    }

    public static AppNetworkService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppNetworkService(context);
        }

        mInstance.context = context;

        return mInstance;
    }

    public boolean cancelRequest(ApiRequestId reqId) {
        if (requestIdPrev != null && requestIdPrev == reqId) {
            jsonRequest.cancel();
            return true;
        }
        return false;
    }

    @Override
    public void getBookingHistory(ArrayMap<String, String> params, ApiResponseHandler apiResponseHandler) {
        this.apiResponseHandler = apiResponseHandler;
        callApi(Request.Method.GET, AppConstants.BOOKING_FILE_URIl, ApiRequestId.GET_BOOKING_DETAILS, params);
    }


    private void callApi(final int get, final String url, final ApiRequestId apiRequestId, final ArrayMap<String, String> mParams) {

        if (GeneralUtil.isNetworkConnectionAvailable(context)) {
            requestIdPrev = apiRequestId;
            VolleySingleton volleySingleton = VolleySingleton.getInstance();
            RequestQueue requestQueue = volleySingleton.getRequestQueue();
            initJsonRequest(get, url, apiRequestId, mParams);
            requestQueue.add(jsonRequest);
            firstErrorMsg = true;
        } else {
            Toast.makeText(context, "Internet not available", Toast.LENGTH_SHORT).show();
        }

    }


    private void initJsonRequest(int get, String url, final ApiRequestId apiReqId, final ArrayMap<String, String> mParams) {
        final String pingUrl = url;

        if (get == Request.Method.GET) {
            url = ApiServiceUtil.constructUrl(url, mParams);
        } else {
            ApiServiceUtil.constructUrl(url, mParams);
        }
        calltime1 = new Date().getTime();
        jsonRequest = new JsonObjectRequest(get, url, new JSONObject(mParams), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                LogUtils.ld(TAG, "Response :" + response);
                //task.cancel(true);
                try {
                    apiResponseHandler.onSuccess(pingUrl, apiReqId, response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        if (context != null && context instanceof Activity) {
//                            GeneralUtil.dismissProgressBar((Activity) context);
//                        }
                        NetworkResponse response = error.networkResponse;
                        if (response != null) {
                            if (response.statusCode == 422) {
                                // Utils.dismissProgressBar(this);
                                String message = new String(response.data);
                                LogUtils.ld(TAG, "Response error:" + message);
                                apiResponseHandler.onFailure(response.statusCode, pingUrl, apiReqId, message);
                            } else if (response.statusCode == 400) {
                                apiResponseHandler.onFailure(response.statusCode, pingUrl, apiReqId, "400 error code");
                            } else {
                                try {
                                    apiResponseHandler.onFailure(response.statusCode, pingUrl, apiReqId, "Oops, something went wrong. Please try again");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (error.toString().equals(TimeoutError.class.getName())) {
                            calltime2 = calltime1 - new Date().getTime();
                            LogUtils.ld("calltime", "" + calltime2);
                        }
                        if (response == null) {
                            LogUtils.fl(TAG, "Response error:" + error);
                        }
                    }
                }) {
            @Override
            protected ArrayMap<String, String> getParams() {
                // mParams.put("","");
                if (mParams != null)
                    LogUtils.ld("params are", "Parameters " + mParams.toString());
                return mParams;
            }

            @Override
            public ArrayMap<String, String> getHeaders() throws AuthFailureError {
                ArrayMap<String, String> headers = new ArrayMap<>();
                headers.put("Content-Type", "application/json");
//                String CUSHY_API_KEY_VALUE = AppPreferences.getString(AppConstants.CUSHY_API_KEY, context);
                /*headers.put(AppConstants.CUSHY_API_KEY, AppConstants.CUSHY_API_KEY_VALUE);
                String deviceId = AppPreferences.getString(AppConstants.DEVICE_ID, context);
                String density = AppPreferences.getString(AppConstants.DEVICE_DENSITY, context);
                String platform = "android";
                String osVersion = android.os.Build.VERSION.RELEASE;
                String deviceModel = android.os.Build.MODEL;
                try {
                    PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                    int appVersion = pkgInfo.versionCode;
                    headers.put("app_version", "" + appVersion);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if (!deviceId.equals("")) {
                    headers.put("device_id", deviceId);
                }

                if (apiReqId == ApiRequestId.LOGIN_USER || apiReqId == ApiRequestId.SIGN_UP_USER) {
                    JSONObject jobj = new JSONObject();
                    try {
                        jobj.put("device_id", deviceId);
                        jobj.put("platform", platform);
                        jobj.put("version", osVersion);
                        jobj.put("density", density);
                        jobj.put("model", deviceModel);
                        headers.put("device_info", jobj.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    String auth_secret = AppPreferences.getString(AppConstants.AUTH_SECRET, context);
                    if (!auth_secret.equals("")) {
                        headers.put(AppConstants.AUTH_SECRET, auth_secret);
                    }
                    if (!density.equals("")) {
                        headers.put("density", density);
                    }

                }*/


                LogUtils.ld(TAG, "Headers " + headers.toString());
                return headers;
            }
        };

//        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        jsonRequest.setShouldCache(true);
        /*if (apiReqId == ApiRequestId.CREATE_CUSHY) {
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_NETWORK_CALL_TIMEOUT_MS, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } else {
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_NETWORK_CALL_TIMEOUT_MS, VOLLEY_NETWORK_CALL_RETRY, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }*/
        LogUtils.ld(TAG, "Ping url " + url);
        LogUtils.ld(TAG, "Params " + mParams.toString());

    }


}
