package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingPassenger {
    @SerializedName("Infant")
    boolean infant;
    @SerializedName("PaxPriceType")
    PaxPriceType paxPriceType;
    @SerializedName("PassengerFees")
    String passengerFees;

    public boolean isInfant() {
        return infant;
    }

    public void setInfant(boolean infant) {
        this.infant = infant;
    }

    public PaxPriceType getPaxPriceType() {
        return paxPriceType;
    }

    public void setPaxPriceType(PaxPriceType paxPriceType) {
        this.paxPriceType = paxPriceType;
    }

    public String getPassengerFees() {
        return passengerFees;
    }

    public void setPassengerFees(String passengerFees) {
        this.passengerFees = passengerFees;
    }
}
