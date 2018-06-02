package com.example.ikit.running;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.ikit.running.Constantes.*;

public class MainActivity extends FragmentActivity {
    private LocationListener myLocationListener;
    private LocationManager locationManager;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialise the main page
        final TextView textView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.main_start_running);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new MyLocationListener();

        //ask the user the permission to use the gps and internet to get his position
        requestPermissions(FINE_PERMISSION_GPS,1);
        requestPermissions(INTERNET,3);

        //we try to initiate the locationUpdate
       try{
           locationManager.requestLocationUpdates("gps", TIME_REFRESH_GPS, MIN_DISTANCE_UPDATE_GPS, myLocationListener);
       }//if we cannot, we have to handle the exception
       catch (SecurityException securityException){
           securityException.printStackTrace();
       }
       //put a listener on the Running view to navigate into the running menu
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRunning();
            }
        });
    }

    public void startRunning(){
        Intent intent = new Intent(this, Running.class);
        startActivity(intent);
    }
}
