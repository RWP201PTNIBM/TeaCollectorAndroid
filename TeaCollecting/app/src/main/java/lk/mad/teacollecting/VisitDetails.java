package lk.mad.teacollecting;

public class VisitDetails {
    int sup_id,noofbags;
    String sup_Name;
    double wieght;

    public VisitDetails(int sup_id, int noofbags, String sup_Name, double wieght) {
        this.sup_id = sup_id;
        this.noofbags = noofbags;
        this.sup_Name = sup_Name;
        this.wieght = wieght;
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public int getNoofbags() {
        return noofbags;
    }

    public void setNoofbags(int noofbags) {
        this.noofbags = noofbags;
    }

    public String getSup_Name() {
        return sup_Name;
    }

    public void setSup_Name(String sup_Name) {
        this.sup_Name = sup_Name;
    }

    public double getWieght() {
        return wieght;
    }

    public void setWieght(double wieght) {
        this.wieght = wieght;
    }
}
