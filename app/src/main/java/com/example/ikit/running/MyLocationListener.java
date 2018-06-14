package com.example.ikit.running;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class MyLocationListener implements LocationListener{

    @Override
    public void onLocationChanged(Location location) {
        double latitude;
        double longitude;

        //we get the latitude and the longitude
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        //add the new location in the singleton
        LocationData.getInstance().setLocationGPS(latitude,longitude);

        if(LocationData.getIsStarted() == false){
            //we initialize LatStart and LngStart with the last known location of the runner when he started the app
            LocationData.getInstance().setLatStart(latitude);
            LocationData.getInstance().setLngStart(longitude);
            LocationData.setIsStared(true);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
