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

    private static final String TAG2 = "Searching for...";
    private static final String SAVED_INSTANCE_KEY = "OnConfiguration Changed";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String BUNDLE_KEY = "Key to the Bundle";
    private String searchViewState;
    private List<Locations> locationsArrayList;
    private LocationAdapter locationAdapter;


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
            locationsArrayList = getArguments().getParcelableArrayList(BUNDLE_KEY);
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
        if (locationsArrayList == null) {
            Toast.makeText(getActivity(), "Please check internet connection", Toast.LENGTH_SHORT).show();
            return false;
        } else {
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

    public interface onRetrofitCall {
        void onRetrofitCreation(List list);

    }

    private void setViews(View v) {
        locationRecyclerView = v.findViewById(R.id.locations_recyclerview);
        searchView = v.findViewById(R.id.search_view);


    }

    private void setupRecyclerView() {
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        locationAdapter = new LocationAdapter(locationsArrayList, mListener);
        locationRecyclerView.setAdapter(locationAdapter);


    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SAVED_INSTANCE_KEY, searchViewState = searchView.getQuery().toString());
        super.onSaveInstanceState(outState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_KEY)) {
            searchView.setQuery(searchViewState, true);
        }

    }

}

