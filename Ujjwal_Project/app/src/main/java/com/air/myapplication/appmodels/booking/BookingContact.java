package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingContact {

    @SerializedName("OtherPhone")
    String otherPhone;
    @SerializedName("EmailAddress")
    String emailAddress;
    @SerializedName("HomePhone")
    String homePhone;
    @SerializedName("WorkPhone")
    String workPhone;
    @SerializedName("Name")
    Name name;

    public String getOtherPhone() {
        return otherPhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public Name getName() {
        return name;
    }
}
