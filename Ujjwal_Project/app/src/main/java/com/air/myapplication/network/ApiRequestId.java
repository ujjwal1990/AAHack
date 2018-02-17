package com.air.myapplication.network;

import java.util.HashMap;
import java.util.Map;

public enum ApiRequestId {
    GET_BOOKING_DETAILS(1);
    private int id;

    private static Map<Integer, ApiRequestId> map = new HashMap<Integer, ApiRequestId>();

    static {
        for (ApiRequestId legEnum : ApiRequestId.values()) {
            map.put(legEnum.id, legEnum);
        }
    }

    ApiRequestId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ApiRequestId valueOf(int legNo) {
        return map.get(legNo);
    }

}

