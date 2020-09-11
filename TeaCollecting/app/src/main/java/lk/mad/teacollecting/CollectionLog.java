package lk.mad.teacollecting;

import java.util.Date;

public class CollectionLog {
    private Date date;
    private double weight;
    private int noOfBags;

    public CollectionLog(Date date, double weight, int noOfBags) {
        this.date = date;
        this.weight = weight;
        this.noOfBags = noOfBags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNoOfBags() {
        return noOfBags;
    }

    public void setNoOfBags(int noOfBags) {
        this.noOfBags = noOfBags;
    }
}
