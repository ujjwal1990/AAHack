package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class Name {
    @SerializedName("MiddleName")
    String middleName;
    @SerializedName("FirstName")
    String firstName;
    @SerializedName("Title")
    String title;
    @SerializedName("LastName")
    String lastName;

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }
}
