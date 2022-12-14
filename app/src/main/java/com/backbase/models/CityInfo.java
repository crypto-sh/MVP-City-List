package com.backbase.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.backbase.utils.LogHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class CityInfo implements Parcelable {

    private Long _id = 0L;
    private String country = "";
    private String name = "";
    private CoordinateInfo coord;

    private final LogHelper logHelper = new LogHelper(this.getClass());

    public CityInfo() {
        coord = new CoordinateInfo(0.0, 0.0);
    }

    public CityInfo(JSONObject json) {
        try {
            country = json.getString("country");
            name = json.getString("name");
            _id = json.getLong("_id");
            coord = new CoordinateInfo(json.getJSONObject("coord"));

        } catch (JSONException e) {
            logHelper.e(e);
        }
    }

    public CityInfo(String country, String name, Long _id, CoordinateInfo coord) {
        this.country = country;
        this.name = name;
        this._id = _id;
        this.coord = coord;
    }

    protected CityInfo(Parcel in) {
        if (in.readByte() == 0) {
            _id = null;
        } else {
            _id = in.readLong();
        }
        country = in.readString();
        name = in.readString();
        coord = in.readParcelable(CoordinateInfo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(_id);
        }
        dest.writeString(country);
        dest.writeString(name);
        dest.writeParcelable(coord, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityInfo> CREATOR = new Creator<CityInfo>() {
        @Override
        public CityInfo createFromParcel(Parcel in) {
            return new CityInfo(in);
        }

        @Override
        public CityInfo[] newArray(int size) {
            return new CityInfo[size];
        }
    };

    public Long get_id() {
        return _id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public CoordinateInfo getCoord() {
        return coord;
    }

    public String getTitle() {
        return name + ", " + country;
    }

    public String getCompareString() {
        return (name + ", " + country).toLowerCase();
    }

    public String getSubTile() {
        return coord.toString();
    }

    @Override
    public String toString() {
        return "CityInfo{ _id=" + _id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", coord=" + coord +
                ", logHelper=" + logHelper +
                '}';
    }
}
