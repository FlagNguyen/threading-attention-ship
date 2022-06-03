package eway.model;

public class Area {
    private String areaCode;
    private int longitudeLeft;
    private int longitudeRight;
    private int latitudeTop;
    private int latitudeBottom;

    public Area() {
    }

    public Area(String areaCode, String longitudeLeft, String longitudeRight, String latitudeTop, String latitudeBottom) {
        this.areaCode = areaCode;
        this.longitudeLeft = Integer.parseInt(longitudeLeft);
        this.longitudeRight = Integer.parseInt(longitudeRight);
        this.latitudeTop = Integer.parseInt(latitudeTop);
        this.latitudeBottom = Integer.parseInt(latitudeBottom);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public int getLongitudeLeft() {
        return longitudeLeft;
    }

    public void setLongitudeLeft(String longitudeLeft) {
        this.longitudeLeft = Integer.parseInt(longitudeLeft);
    }

    public int getLongitudeRight() {
        return longitudeRight;
    }

    public void setLongitudeRight(String longitudeRight) {
        this.longitudeRight = Integer.parseInt(longitudeRight);
    }

    public int getLatitudeTop() {
        return latitudeTop;
    }

    public void setLatitudeTop(String latitudeTop) {
        this.latitudeTop = Integer.parseInt(latitudeTop);
    }

    public int getLatitudeBottom() {
        return latitudeBottom;
    }

    public void setLatitudeBottom(String latitudeBottom) {
        this.latitudeBottom = Integer.parseInt(latitudeBottom);
    }

    @Override
    public String toString() {
        return getAreaCode() + "("
                + getLongitudeLeft() + "|"
                + getLongitudeRight() + "|"
                + getLatitudeTop() + "|"
                + getLatitudeBottom() + ")";
    }
}
