package org.pursuit.cliffordcharles_finalassessment.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.controller.LocationAdapter;
import org.pursuit.cliffordcharles_finalassessment.fragment.GoogleMapFragment;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnLocationFragmentInteractionListener, LocationFragment.onRetrofitCall {
    Bundle newBundy = new Bundle();
    private ViewDialogue viewDialogue;
    private static final String TAG = "Connection status";
    private static final String BUNDLE_KEY = "Key to the Bundle";
    private List<Locations> locationsArrayList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewDialogue = new ViewDialogue(this);
        showCustomLoadingDialog();
        setupRetrofit();


    }


    @Override
    public void onLocationFragmentInteraction(String lat, String lon) {

        showCustomLoadingDialog();
        GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance(lat, lon);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("MapFragment")
                .replace(R.id.main_container, googleMapFragment)
                .commit();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("Orientation", "Current Orientation : Landscape");


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("newBundy", newBundy);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getBundle("newBundy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    public void setupRetrofit() {

        RetrofitSingleton.getInstance()
                .getLocationService()
                .getLocations()
                .enqueue(new Callback<List<Locations>>() {
                    @Override
                    public void onResponse(Call<List<Locations>> call, Response<List<Locations>> response) {
                        Log.d(TAG, "OnResponse" + response.body().get(0).getCountry());
                        if (response.body() == null) {
                            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();


                        } else {
                            if (locationsArrayList == null) {
                                locationsArrayList = new ArrayList<>();
                                locationsArrayList.addAll(response.body());
                            }
                        }
                        Collections.sort(locationsArrayList);
                        onRetrofitCreation(locationsArrayList);


                    }

                    @Override
                    public void onFailure(Call<List<Locations>> call, Throwable t) {
                        Log.d(TAG, "OnFailure" + t.getMessage());
                        Toast.makeText(getApplication(), "Connection Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onRetrofitCreation(List list) {

        ArrayList<Locations> sortedList = new ArrayList<>(locationsArrayList.size());
        sortedList.addAll(locationsArrayList);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, sortedList);
        LocationFragment locationFragment = new LocationFragment();
        locationFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, locationFragment)
                .commit();

    }

    public void showCustomLoadingDialog() {


        viewDialogue.showDialogue();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewDialogue.hideDialogue();
            }
        }, 2000);
    }

}

