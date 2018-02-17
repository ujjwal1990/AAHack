package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class Segment {
    @SerializedName("DepartureStation")
    String departureStation;
    @SerializedName("ArrivalStation")
    String arrivalStation;
    @SerializedName("FlightDesignator")
    FlightDesignator flightDesignator;
    @SerializedName("DepartureTime")
    String departureTime;
    @SerializedName("ArrivalTime")
    String arrivalTime;

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public FlightDesignator getFlightDesignator() {
        return flightDesignator;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
}
