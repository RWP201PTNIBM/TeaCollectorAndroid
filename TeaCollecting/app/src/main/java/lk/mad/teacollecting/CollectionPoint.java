package lk.mad.teacollecting;

public class CollectionPoint {
    private int cp_id;
    private String cp_name;
    private double lng;
    private  double lat;

    public CollectionPoint(int cp_id, String cp_name, double lng, double lat) {
        this.cp_id = cp_id;
        this.cp_name = cp_name;
        this.lng = lng;
        this.lat = lat;
    }

    public int getCp_id() {
        return cp_id;
    }

    public void setCp_id(int cp_id) {
        this.cp_id = cp_id;
    }

    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
