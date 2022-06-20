package src.model;

class Ship{
    private String shipCode;
    private int longitude;
    private int latitude;
    private String time;


    Ship (String shipCode, int longitude, int latitude, String time) {
        this.shipCode = shipCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

    String getShipCode() {
        return shipCode
    }

    int getLongitude() {
        return longitude
    }

    int getLatitude() {
        return latitude
    }

    String getTime() {
        return time
    }

    @Override
    String toString() {
        return shipCode + "={" +
                "longitude='" + longitude + "|" +
                ", latitude='" + latitude +
                "} |" + time ;
    }
}
