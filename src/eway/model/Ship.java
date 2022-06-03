package eway.model;

import eway.constant.Constant;

import java.util.Map;

public class Ship{
    private String shipCode;
    private int longitude;
    private int latitude;
    private String time;


    public Ship(String shipCode, int longitude, int latitude, String time) {
        this.shipCode = shipCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }


    public Ship() {
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return shipCode + "={" +
                "longitude='" + longitude + "|" +
                ", latitude='" + latitude +
                "} |" + time ;
    }
}
