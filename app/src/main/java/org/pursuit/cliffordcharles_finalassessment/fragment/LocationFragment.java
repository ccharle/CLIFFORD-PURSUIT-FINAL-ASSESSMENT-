package org.pursuit.cliffordcharles_finalassessment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.controller.LocationAdapter;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;
import org.pursuit.cliffordcharles_finalassessment.view.ViewDialogue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView locationRecyclerView;
    private android.support.v7.widget.SearchView searchView;
    private static final String TAG = "Connection status";
    private static final String TAG2 = "Searching for...";
    private LocationAdapter locationAdapter;
    private List<Locations> locationsArrayList = null;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewDialogue viewDialogue;


    private String mParam1;
    private String mParam2;

    private OnLocationFragmentInteractionListener mListener;

    public LocationFragment() {
        // Required empty public constructor
    }

    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLocationFragmentInteractionListener) {
            mListener = (OnLocationFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLocationFragmentInteractionListener");
        }


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
        setupRecyclerView();
        setupRetrofit();
        searchView.setOnQueryTextListener(this);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;

    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d(TAG2, s);
        {
            List<Locations> newLocationsList = new ArrayList<>();
            for (Locations locations : locationsArrayList) {
                if (locations.getName().toLowerCase().startsWith(s.toLowerCase())) {
                    newLocationsList.add(locations);
                }

            }
            locationAdapter.setData(newLocationsList);
        }


        return false;
    }


    public interface OnLocationFragmentInteractionListener {
        void onLocationFragmentInteraction(String Lat, String Lon);
    }

    private void setViews(View v) {
        locationRecyclerView = v.findViewById(R.id.locations_recyclerview);
        searchView = v.findViewById(R.id.search_view);


    }

    private void setupRecyclerView() {

        locationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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
                            Toast.makeText(getActivity(), "Connection Error", Toast.LENGTH_SHORT).show();


                        }
                        viewDialogue = new ViewDialogue(getActivity());
                        if (locationsArrayList == null) {
                            locationsArrayList = new ArrayList<>();
                            locationsArrayList.addAll(response.body());
                        }
                        Collections.sort(locationsArrayList);
                        locationAdapter = new LocationAdapter(locationsArrayList, mListener);
                        locationRecyclerView.setAdapter(locationAdapter);
                        showCustomLoadingDialog();

                    }

                    @Override
                    public void onFailure(Call<List<Locations>> call, Throwable t) {
                        Log.d(TAG, "OnFailure" + t.getMessage());
                        Toast.makeText(getActivity(), "Connection Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void showCustomLoadingDialog() {


        viewDialogue.showDialogue();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewDialogue.hideDialogue();
            }
        }, 1000);
    }
}

