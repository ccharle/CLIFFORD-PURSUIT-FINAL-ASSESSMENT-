package org.pursuit.cliffordcharles_finalassessment.model;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {

    private String lon;
    private String lat;

    public Coordinates(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}
