package com.backbase.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.backbase.utils.LogHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class CoordinateInfo implements Parcelable {

    private Double lon;
    private Double lat;

    private final LogHelper logHelper = new LogHelper(this.getClass());

    public CoordinateInfo(JSONObject json){
        try {
            lon = json.getDouble("lon");
            lat = json.getDouble("lat");
        } catch (JSONException e) {
            logHelper.e(e);
        }
    }

    public CoordinateInfo(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    protected CoordinateInfo(Parcel in) {
        if (in.readByte() == 0) {
            lon = null;
        } else {
            lon = in.readDouble();
        }
        if (in.readByte() == 0) {
            lat = null;
        } else {
            lat = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (lon == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lon);
        }
        if (lat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lat);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CoordinateInfo> CREATOR = new Creator<CoordinateInfo>() {
        @Override
        public CoordinateInfo createFromParcel(Parcel in) {
            return new CoordinateInfo(in);
        }

        @Override
        public CoordinateInfo[] newArray(int size) {
            return new CoordinateInfo[size];
        }
    };

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "lon : " + lon + " lat : " + lat;
    }
}
