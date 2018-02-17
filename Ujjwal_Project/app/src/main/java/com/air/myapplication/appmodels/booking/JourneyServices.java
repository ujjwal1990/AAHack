package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class JourneyServices {
    @SerializedName("JourneyService")
    ArrayList<JourneyService> journeyService;

    public ArrayList<JourneyService> getJourneyService() {
        return journeyService;
    }

  }
