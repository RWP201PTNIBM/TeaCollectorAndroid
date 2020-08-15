package lk.mad.teacollecting;

public class Supplier {
    int sup_id;
    String sup_Name;
    String sup_Add;
    int visit_id;

    public Supplier(int sup_id, String sup_Name, String sup_Add) {
        this.sup_id = sup_id;
        this.sup_Name = sup_Name;
        this.sup_Add = sup_Add;
    }

    public Supplier(int sup_id, String sup_Name, String sup_Add, int visit_id) {
        this.sup_id = sup_id;
        this.sup_Name = sup_Name;
        this.sup_Add = sup_Add;
        this.visit_id = visit_id;
    }

    public int getSup_id() {
        return sup_id;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getSup_Name() {
        return sup_Name;
    }

    public void setSup_Name(String sup_Name) {
        this.sup_Name = sup_Name;
    }

    public String getSup_Add() {
        return sup_Add;
    }

    public void setSup_Add(String sup_Add) {
        this.sup_Add = sup_Add;
    }
}
