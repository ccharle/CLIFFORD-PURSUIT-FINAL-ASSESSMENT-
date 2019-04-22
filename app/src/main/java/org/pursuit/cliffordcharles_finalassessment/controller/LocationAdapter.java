package org.pursuit.cliffordcharles_finalassessment.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.view.LocationViewHolder;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private LocationFragment.OnLocationFragmentInteractionListener locationFragmentInteractionListener;

    private List<Locations> locationsList;

    public LocationAdapter(List<Locations> locationsList, LocationFragment.OnLocationFragmentInteractionListener onLocationFragmentInteractionListener) {
        this.locationsList = locationsList;
        this.locationFragmentInteractionListener = onLocationFragmentInteractionListener;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.locations_itemview, viewGroup, false);
        return new LocationViewHolder(childView, locationFragmentInteractionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int i) {
        Locations locations = locationsList.get(i);
        locationViewHolder.onBind(locations);

    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }


    public void setData(List<Locations> newLocationList) {
        this.locationsList = newLocationList;
        notifyDataSetChanged();
    }

}
