package org.pursuit.cliffordcharles_finalassessment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.controller.LocationAdapter;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.LocationService;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LocationFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView locationRecyclerView;
    private android.support.v7.widget.SearchView searchView;
    private static final String TAG = "Connection status";
    private static final String TAG2 = "Searching for...";
    private LocationAdapter locationAdapter;
    private List<Locations> locationsArrayList = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/


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
        Log.d(TAG2,s);
        List<Locations> newLocationsList = new ArrayList<>();
        for (Locations locations : locationsArrayList) {
            if (locations.getName().toLowerCase().startsWith(s.toLowerCase())) {
                newLocationsList. add(locations);
            }
        }

        locationAdapter.setData(newLocationsList);
        return false;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
                        locationsArrayList.addAll(response.body());
                        Collections.sort(locationsArrayList);
                        locationAdapter = new LocationAdapter(locationsArrayList);
                        locationRecyclerView.setAdapter(locationAdapter);



                    }

                    @Override
                    public void onFailure(Call<List<Locations>> call, Throwable t) {
                        Log.d(TAG, "OnFailure" + t.getMessage());
                        Toast.makeText(getActivity(), "Connection Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}

