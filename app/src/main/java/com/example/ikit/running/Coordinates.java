package com.example.ikit.running;

public class Coordinates {
    private double latitude;
    private double longitude;

    public void Coordinates(double lat, double lng){
        latitude = lat;
        longitude = lng;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
