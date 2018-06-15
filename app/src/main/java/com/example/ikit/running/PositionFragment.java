package com.example.ikit.running;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class PositionFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap gMap;
    boolean mapReady = false;

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
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void displayCoordinates(){
        FileOutputStream outputStream;

        //we create a file named YEAR + MONTH + DAY + HOUR + MINUTE
        Calendar date = Calendar.getInstance();
        String nameFile = "";
        nameFile+=date.get(Calendar.YEAR);
        nameFile+=date.get(Calendar.MONTH);
        nameFile+=date.get(Calendar.DAY_OF_MONTH);
        nameFile+=date.get(Calendar.HOUR_OF_DAY);
        nameFile+=date.get(Calendar.MINUTE);
        //String that contain the entire track
        String trackToSave = "";
        TextView textView = getView().findViewById(R.id.show_coordonates);
        LatLng lastKnownPosition = new LatLng(LocationData.getInstance().getLatStart(),LocationData.getInstance().getLngStart());
        PolylineOptions options = new PolylineOptions();
        ArrayList<GPSLocationData> listCoordinates = LocationData.getInstance().getLocationGPS();
        int i;
        if(mapReady){
            if(listCoordinates.size()>0) {
                //build the track of the race
                for (i = 0; i < listCoordinates.size(); i++) {
                    lastKnownPosition = new LatLng(listCoordinates.get(i).getLatitude(),
                            listCoordinates.get(i).getLongitude());
                    options.add(lastKnownPosition);
                    //we add the coordinates to the trackToSave with a # to separate Lat and Long and / to separate different points
                    trackToSave += "" + lastKnownPosition.latitude + "#" + lastKnownPosition.longitude + "/";
                }

                //put the camera to the last position of the runner
                CameraPosition cameraPosition = CameraPosition.builder()
                        .target(lastKnownPosition)
                        .zoom(14)
                        .build();
                gMap.moveCamera(
                        CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
                gMap.addPolyline(options);
                textView.setText(trackToSave);
                File dir = getActivity().getApplicationContext().getDir(Constantes.DIR_NAME_TRACK, MODE_PRIVATE);
                File file = new File(dir, nameFile);
                Log.d("FILE", "directory name " + dir.getName().toString());
                try {
                    outputStream = new FileOutputStream(file);
                    outputStream.write(trackToSave.getBytes());
                    outputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                textView.setText(trackToSave);
            }
            else{
                textView.setText("Aucun tracé n'a été enregistré");
            }
        }
        else{
            textView.setText("La map n'est pas encore prete");
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        gMap = googleMap;
    }
}
