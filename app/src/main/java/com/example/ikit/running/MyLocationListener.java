package com.example.ikit.running;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;


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
