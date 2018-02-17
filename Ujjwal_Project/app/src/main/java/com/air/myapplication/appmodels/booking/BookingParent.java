package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingParent implements Serializable{
    @SerializedName("BookingStatus")
    String bookingStatus;
    @SerializedName("PNR")
    String pnr;
    @SerializedName("BookingContacts")
    BookingContacts bookingContacts;
    @SerializedName("BookingPassengers")
    BookingPassengers bookingPassengers;
    @SerializedName("JourneyServices")
    JourneyServices journeyServices;

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getPnr() {
        return pnr;
    }

    public BookingContacts getBookingContacts() {
        return bookingContacts;
    }

    public BookingPassengers getBookingPassengers() {
        return bookingPassengers;
    }

    public JourneyServices getJourneyServices() {
        return journeyServices;
    }
}
