package com.example.ikit.running;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;


public class PositionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        final View view;
        view = layoutInflater.inflate(R.layout.fragment_position_layout, container, false);
        Button button = view.findViewById(R.id.button_show_coordonates);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCoordinates();
            }
        });
        return view;
    }

    public void displayCoordinates(){
        TextView textView = getView().findViewById(R.id.show_coordonates);
        ArrayList<GPSLocationData> listCoordinates = LocationData.getInstance().getLocationGPS();
        int i;
        for(i=0; i< listCoordinates.size(); i++){
            textView.append("\nlongitude : "+ listCoordinates.get(i).getLongitude());
            textView.append("\nlatitude : "+ listCoordinates.get(i).getLatitude());
        }
    }
}
