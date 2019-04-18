package org.pursuit.cliffordcharles_finalassessment.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static RetrofitSingleton ourInstance = null;
    private LocationService locationService;

    private RetrofitSingleton() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        locationService = retrofit.create(LocationService.class);
    }

    public static RetrofitSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new RetrofitSingleton();


        }
        return ourInstance;


    }

    public LocationService getLocationService() {
        return locationService;
    }


}
