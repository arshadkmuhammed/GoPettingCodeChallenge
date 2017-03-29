package com.arshad.free.gopettingcodechallenge.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arshad on 29/3/17.
 */

public class UpcomingGuideResponse implements Serializable {

    @Expose
    @SerializedName("total")
    private String totalCount;

    @Expose
    @SerializedName("data")
    private ArrayList<UpcomingGuide> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<UpcomingGuide> getData() {
        return data;
    }

    public void setData(ArrayList<UpcomingGuide> data) {
        this.data = data;
    }
}
