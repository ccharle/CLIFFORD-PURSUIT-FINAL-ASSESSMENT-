package org.pursuit.cliffordcharles_finalassessment.view;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.fragment.GoogleMapFragment;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnLocationFragmentInteractionListener {
    Bundle newBundy = new Bundle();
    private ViewDialogue viewDialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationFragment locationFragment = new LocationFragment();


        swapFragment(locationFragment);


    }


    @Override
    public void onLocationFragmentInteraction(String lat, String lon) {
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
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
}

