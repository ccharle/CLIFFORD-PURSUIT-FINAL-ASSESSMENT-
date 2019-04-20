package org.pursuit.cliffordcharles_finalassessment.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pursuit.cliffordcharles_finalassessment.R;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    private TextView countryTextView, nameTextView, _idTextView, longitudeTextView, latitudeTextView;

    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);
        setViews();

    }

    public void onBind(Locations locations) {
        String name = locations.getName();
        String country = locations.getCountry();

        countryTextView.setText(name + " , " + country);
     /*   _idTextView.setText((String.valueOf(locations.get_id())));
        latitudeTextView.setText(locations.getCoord().getLat());
        longitudeTextView.setText(locations.getCoord().getLon());*/


    }

    public void setViews() {
        countryTextView = itemView.findViewById(R.id.country_textview);
        nameTextView = itemView.findViewById(R.id.name_textview);
        _idTextView = itemView.findViewById(R.id._id_textview);
        longitudeTextView = itemView.findViewById(R.id.longitude_textview);
        latitudeTextView = itemView.findViewById(R.id.latitude_textview);

    }
}
