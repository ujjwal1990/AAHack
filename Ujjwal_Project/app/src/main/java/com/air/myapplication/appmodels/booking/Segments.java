package com.air.myapplication.appmodels.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class Segments {
    @SerializedName("Segment")
    ArrayList<Segment> segment;

    public ArrayList<Segment> getSegment() {
        return segment;
    }
}
