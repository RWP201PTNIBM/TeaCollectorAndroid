package lk.mad.teacollecting;

public class Location {
    double lat;
    double lon;
    String name;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Location(double lat, double lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() { return this.name; }

    public void setName(String name) {
        this.name = name;
    }
}
