package org.pursuit.cliffordcharles_finalassessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Connection status";

    private RetrofitSingleton retrofitSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RetrofitSingleton.getInstance()
                .getLocationService()
                .getLocations()
                .enqueue(new Callback<List<Locations>>() {
                    @Override
                    public void onResponse(Call<List<Locations>> call, Response<List<Locations>> response) {
                        Log.d(TAG, "OnResponse" + response.body());

                    }

                    @Override
                    public void onFailure(Call<List<Locations>> call, Throwable t) {
                        Log.d(TAG, "Onfailure" + t.getMessage());
                    }
                });
    }


}
