package org.pursuit.cliffordcharles_finalassessment.network;


import org.pursuit.cliffordcharles_finalassessment.model.Locations;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationService {
    @GET("/joinpursuit/Pursuit-Core-Android-Unit6-CTA-Bank-Locator/master/location.json")
    Call <List <Locations> > getLocations();

}
