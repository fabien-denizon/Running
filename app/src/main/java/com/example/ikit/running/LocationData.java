package com.example.ikit.running;

import java.util.ArrayList;
//Singleton that is an ArraList of 2 double value, latitude and longitude
public class LocationData {

    private static LocationData INSTANCE = null;
    private static ArrayList<GPSLocationData> locationGPS = new ArrayList<GPSLocationData>();
    private static double LatStart;
    private static double LngStart;
    private static boolean isStared = false;

    public static void setIsStared(boolean isStared) {
        LocationData.isStared = isStared;
    }

    public static boolean getIsStarted(){
        return isStared;
    }

    public double getLatStart() {
        return LatStart;
    }

    public double getLngStart() {
        return LngStart;
    }

    public void setLatStart(double latStart) {
        LatStart = latStart;
    }

    public void setLngStart(double lngStart) {
        LngStart = lngStart;
    }

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
