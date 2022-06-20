package src.model;

class Area {
    private String areaCode;
    private int longitudeLeft;
    private int longitudeRight;
    private int latitudeTop;
    private int latitudeBottom;

    Area(String areaCode, String longitudeLeft, String longitudeRight, String latitudeTop, String latitudeBottom) {
        this.areaCode = areaCode;
        this.longitudeLeft = Integer.parseInt(longitudeLeft);
        this.longitudeRight = Integer.parseInt(longitudeRight);
        this.latitudeTop = Integer.parseInt(latitudeTop);
        this.latitudeBottom = Integer.parseInt(latitudeBottom);
    }

    String getAreaCode() {
        return areaCode;
    }

    int getLongitudeLeft() {
        return longitudeLeft;
    }

    int getLongitudeRight() {
        return longitudeRight;
    }

    int getLatitudeTop() {
        return latitudeTop;
    }

    int getLatitudeBottom() {
        return latitudeBottom;
    }

    @Override
    String toString() {
        return getAreaCode() + "("
                + getLongitudeLeft() + "|"
                + getLongitudeRight() + "|"
                + getLatitudeTop() + "|"
                + getLatitudeBottom() + ")";
    }
}
