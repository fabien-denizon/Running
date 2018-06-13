package com.example.ikit.running;


public class Constantes {
    public static final long TIME_REFRESH_GPS = 100; //time that will be used in the requestUpdateLocation
    public static final float MIN_DISTANCE_UPDATE_GPS = 1; // minimum distance needed to refresh the gps location
    public static final String[] FINE_PERMISSION_GPS = {android.Manifest.permission.ACCESS_FINE_LOCATION};
    public static final String[] INTERNET = {android.Manifest.permission.INTERNET};
    public static final String DIR_NAME_TRACK = "tracks";
}
