package org.pursuit.cliffordcharles_finalassessment.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.fragment.LocationFragment;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    private TextView countryTextView, nameTextView, _idTextView, longitudeTextView, latitudeTextView;
    private LocationFragment.OnLocationFragmentInteractionListener mListener;
    private String name;
    private String country;

    public LocationViewHolder(@NonNull final View itemView,LocationFragment.OnLocationFragmentInteractionListener onLocationFragmentInteractionListener) {
        super(itemView);
        this.mListener = onLocationFragmentInteractionListener;
        setViews();

    }

    public void onBind(final Locations locations) {
        name = locations.getName();
        country = locations.getCountry();

        countryTextView.setText(name + " , " + country);
     /*   _idTextView.setText((String.valueOf(locations.get_id())));
        latitudeTextView.setText(locations.getCoord().getLat());
        longitudeTextView.setText(locations.getCoord().getLon());*/
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String curentLat =locations.getCoord().getLat();
                String currentLon = locations.getCoord().getLon();
                mListener.onLocationFragmentInteraction(curentLat,currentLon);

            }
        });


    }

    public void setViews() {
        countryTextView = itemView.findViewById(R.id.country_textview);
        nameTextView = itemView.findViewById(R.id.name_textview);
        _idTextView = itemView.findViewById(R.id._id_textview);
        longitudeTextView = itemView.findViewById(R.id.longitude_textview);
        latitudeTextView = itemView.findViewById(R.id.latitude_textview);

    }
}
