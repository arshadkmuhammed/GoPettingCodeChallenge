package com.arshad.free.gopettingcodechallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arshad on 29/3/17.
 */

public class UpcomingGuide implements Serializable,Parcelable, Comparable<UpcomingGuide>{

    @Expose
    @SerializedName("icon")
    private String icon;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("endDate")
    private String endDate;

    protected UpcomingGuide(Parcel in) {
        icon = in.readString();
        name = in.readString();
        endDate = in.readString();
    }

    public static final Creator<UpcomingGuide> CREATOR = new Creator<UpcomingGuide>() {
        @Override
        public UpcomingGuide createFromParcel(Parcel in) {
            return new UpcomingGuide(in);
        }

        @Override
        public UpcomingGuide[] newArray(int size) {
            return new UpcomingGuide[size];
        }
    };

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int compareTo(UpcomingGuide o) {
        return o.getName().compareTo(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UpcomingGuide)) {
            return false;
        }

        UpcomingGuide item = (UpcomingGuide) o;

        return item.name.equalsIgnoreCase(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(name);
        dest.writeString(endDate);
    }
}
