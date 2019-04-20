package org.pursuit.cliffordcharles_finalassessment.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.controller.LocationAdapter;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.LocationService;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationFragment locationFragment = new LocationFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, locationFragment)
                .commit();


    }


}
