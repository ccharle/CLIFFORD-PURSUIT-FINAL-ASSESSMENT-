package org.pursuit.cliffordcharles_finalassessment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.fragment.GoogleMapFragment;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnLocationFragmentInteractionListener {
    private static final String firstParam = "ParamOne";
    private static final String secondParam = "ParamTwo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationFragment locationFragment = new LocationFragment();
        swapFragment(locationFragment);


    }


    @Override
    public void onLocationFragmentInteraction(String lat, String lon) {
//        Intent intent = new Intent(MainActivity.this, MapsFragment.class);
//        intent.putExtra(firstParam, lat);
//        intent.putExtra(secondParam, lon);
//        startActivity(intent);
        GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance(lat, lon);
       swapFragment(googleMapFragment); getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, googleMapFragment)
                .commit();

    }

    public void swapFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack("mainfragment")
                .commit();

    }
}
