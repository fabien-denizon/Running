package com.example.ikit.running;

public class GPSLocationData {
    public double Latitude;
    public double Longitude;

    public GPSLocationData(double latitude, double longitude){
        Latitude = latitude;
        Longitude = longitude;
    }

    public double getLatitude(){
        return Latitude;
    }

    public double getLongitude(){
        return Longitude;
    }
}
