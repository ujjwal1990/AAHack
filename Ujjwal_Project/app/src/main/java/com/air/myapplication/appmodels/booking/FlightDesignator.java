package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class FlightDesignator {
    @SerializedName("FlightNumber")
    String FlightNumber;

    public String getFlightNumber() {
        return FlightNumber;
    }
}
