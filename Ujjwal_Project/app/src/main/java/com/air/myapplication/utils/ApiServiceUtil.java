package com.air.myapplication.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class ApiServiceUtil {
    @NonNull
    public static String constructUrl(String url, ArrayMap<String, String> mParams) {
        StringBuilder builder = new StringBuilder();

        if (mParams != null) {
            for (String key : mParams.keySet()) {
                String value = mParams.get(key);
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");


                        if (builder.length() > 0)
                            builder.append("&");
                        builder.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            }
        }

        if (url.contains("?")) {
            url += "&" + builder.toString();
        } else {
            url += "?" + builder.toString();
        }
        return url;
    }
}
