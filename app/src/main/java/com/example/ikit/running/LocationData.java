package com.example.ikit.running;

import java.util.ArrayList;
//Singleton that is an ArraList of 2 double value, latitude and longitude
public class LocationData {

    private static LocationData INSTANCE = null;
    private static ArrayList<GPSLocationData> locationGPS = new ArrayList<GPSLocationData>();
    private LocationData(){};

    public static synchronized  LocationData getInstance(){
        //if there is no instance of the singleton, we create one
        if(INSTANCE == null){
            INSTANCE = new LocationData();
        }
        return (INSTANCE);
    }

    public void setLocationGPS(double latitude, double longitude){
        GPSLocationData gpsLocationData = new GPSLocationData(latitude, longitude);
        locationGPS.add(gpsLocationData);
    }

    public static ArrayList<GPSLocationData> getLocationGPS() {
        return locationGPS;
    }
}
