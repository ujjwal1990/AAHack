package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingPassengers {
    @SerializedName("BookingPassenger")
    ArrayList<BookingPassenger> bookingPassenger;

    public ArrayList<BookingPassenger> getBookingPassenger() {
        return bookingPassenger;
    }
}
