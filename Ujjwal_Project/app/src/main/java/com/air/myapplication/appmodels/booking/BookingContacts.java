package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingContacts {
    @SerializedName("BookingContact")
    ArrayList<BookingContact> bookingContact;

    public ArrayList<BookingContact> getBookingContact() {
        return bookingContact;
    }


}
