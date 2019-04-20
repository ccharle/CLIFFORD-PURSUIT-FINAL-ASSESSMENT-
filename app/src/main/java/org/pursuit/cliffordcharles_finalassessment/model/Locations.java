package org.pursuit.cliffordcharles_finalassessment.model;

import java.util.ArrayList;
import java.util.List;

    public class Locations {

        private String country;
        private String name;
        private Integer _id;
        private Coordinates coord;

        public String getCountry() {
            return country;
        }

        public String getName() {
            return name;
        }

        public Integer get_id() {
            return _id;
        }

        public Coordinates getCoord() {
            return coord;
        }

        public Locations(String country, String name, Integer _id, Coordinates coord) {
            this.country = country;
            this.name = name;
            this._id = _id;
            this.coord = coord;


        }

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

        public class CoordinatesResponse {


            private List <Coordinates> coord;


            public CoordinatesResponse(ArrayList<Coordinates> coord) {
                this.coord = coord;
            }

            public List<Coordinates> getCoordResponse() {
                return coord;
            }


        }
    }
