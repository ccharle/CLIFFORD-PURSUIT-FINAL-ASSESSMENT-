package org.pursuit.cliffordcharles_finalassessment.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.fragment.GoogleMapFragment;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnLocationFragmentInteractionListener {
    private static final String firstParam = "ParamOne";
    private static final String secondParam = "ParamTwo";
    private ViewDialogue viewDialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationFragment locationFragment = LocationFragment.newInstance(null, null);


        swapFragment(locationFragment);



    }


    @Override
    public void onLocationFragmentInteraction(String lat, String lon) {
//        Intent intent = new Intent(MainActivity.this, MapsFragment.class);
//        intent.putExtra(firstParam, lat);
//        intent.putExtra(secondParam, lon);
//        startActivity(intent);
        viewDialogue = new ViewDialogue(this);
        showCustomLoadingDialog();
        GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance(lat, lon);
        swapFragment(googleMapFragment);
        getSupportFragmentManager()
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

    public void showCustomLoadingDialog() {

        //..show gif
        viewDialogue.showDialogue();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //...here i'm waiting 5 seconds before hiding the custom dialog
                //...you can do whenever you want or whenever your work is done
                viewDialogue.hideDialogue();
            }
        }, 5000);
    }
}
