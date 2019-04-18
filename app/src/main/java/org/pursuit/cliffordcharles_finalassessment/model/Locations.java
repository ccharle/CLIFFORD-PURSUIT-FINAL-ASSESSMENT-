package org.pursuit.cliffordcharles_finalassessment.model;

import java.util.ArrayList;
import java.util.List;

    public class Locations {

        private String country;
        private String name;
        private Integer _id;
        private CoordinatesResponse coord;

        public String getCountry() {
            return country;
        }

        public String getName() {
            return name;
        }

        public Integer get_id() {
            return _id;
        }

        public CoordinatesResponse getCoord() {
            return coord;
        }

        public Locations(String country, String name, Integer _id, CoordinatesResponse coord) {
            this.country = country;
            this.name = name;
            this._id = _id;
            this.coord = coord;


        }

        public class Coordinates {

            private Float lon;
            private Float lat;

            public Coordinates(Float lon, Float lat) {
                this.lon = lon;
                this.lat = lat;
            }

            public Float getLon() {
                return lon;
            }

            public Float getLat() {
                return lat;
            }
        }

        public class CoordinatesResponse {


            private ArrayList<Coordinates> coord;


            public CoordinatesResponse(ArrayList<Coordinates> coord) {
                this.coord = coord;
            }

            public ArrayList<Coordinates> getCoord() {
                return coord;
            }


        }
    }
