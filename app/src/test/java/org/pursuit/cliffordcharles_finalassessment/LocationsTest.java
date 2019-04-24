package org.pursuit.cliffordcharles_finalassessment;

import android.os.Parcel;
import android.os.Parcelable;

import org.pursuit.cliffordcharles_finalassessment.model.Coordinates;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;

public class LocationsTest implements Comparable<LocationsTest> {

    private String country;
    private String name;
    private Integer _id;
    private CoordinatesTest coord;


    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public Integer get_id() {
        return _id;
    }


    public CoordinatesTest getCoord() {
        return coord;
    }

    public LocationsTest(String country, String name, Integer _id, CoordinatesTest coord) {
        this.country = country;
        this.name = name;
        this._id = _id;
        this.coord = coord;
    }




    @Override
    public int compareTo(LocationsTest locations) {
        if (this._id > locations.get_id()){
            return 1;
        } else if (this._id < locations.get_id()) {
            return -1;
        } else {
            return 0;
        }

    }
}
