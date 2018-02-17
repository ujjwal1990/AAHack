package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class PaxPriceType {
    @SerializedName("PaxType")
    String PaxType;

    public String getPaxType() {
        return PaxType;
    }
}
