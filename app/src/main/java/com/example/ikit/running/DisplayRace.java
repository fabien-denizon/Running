package com.example.ikit.running;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;


public class DisplayRace extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap gMap;
    boolean mapReady = false;
    private String raceName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);
        raceName = getIntent().getExtras().getString("race");
        TextView textView = findViewById(R.id.display_layout_text_view);
        textView.setText("*"+raceName+"*");


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment_race);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        mapReady = true;
       /* LatLng test = new LatLng(43.6102356, 1.4814695);
        gMap.addMarker(new MarkerOptions()
                .position(test)
                .title("Chez Moi"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(test));*/
        displayRaceOnMap();
    }

    public void displayRaceOnMap(){
        String[] fileCoordinates;
        PolylineOptions options = new PolylineOptions();
        LatLng startPoint = new LatLng(0,0);
        boolean isStartPoint=true;


        //get the file
        Context ctx = getApplicationContext();
        File file = new File(ctx.getDir(Constantes.DIR_NAME_TRACK,MODE_PRIVATE),raceName);

        TextView textView = findViewById(R.id.display_layout_text_view);
        //get the file
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //if the file doesn't exist tell the user
            if(!file.exists()){
                textView.append("le fichier n'existe pas");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String currentLine;
            //get the content of the file in the stringBuilder
            while ((currentLine = bufferedReader.readLine()) != null){
                stringBuilder.append(currentLine);
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            //You'll need to add proper error handling here
            Log.d("ErreurLecture", "Erreur lors de la lecture du fichier "+raceName);
        }

        //parse it to put the track into a list of coordinates
        fileCoordinates = stringBuilder.toString().split("/");
        String[] tmp;
        for(int i=0;i<fileCoordinates.length;i++){

            tmp = fileCoordinates[i].split("#");
            if(isStartPoint){
                startPoint = new LatLng(parseDouble(tmp[0]), parseDouble(tmp[1]));
                options.add(startPoint);
                isStartPoint=false;
            }else{
                options.add((new LatLng(parseDouble(tmp[0]), parseDouble(tmp[1]))));
            }
        }
        //display the polyline
        gMap.addMarker(new MarkerOptions()
                    .position(startPoint)
                    .title("Point de départ"));
        gMap.addPolyline(options);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(startPoint));
    }
}
