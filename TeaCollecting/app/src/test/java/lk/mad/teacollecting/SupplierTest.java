package lk.mad.teacollecting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SupplierTest {

    @Test
    public void getSup_id() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getSup_id();

        // ...then the result should be the expected one.
        assertThat(result, is(sup_id));
    }

    @Test
    public void getVisit_id() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        int visit_id = 45;
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr,visit_id);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getVisit_id();

        // ...then the result should be the expected one.
        assertThat(result, is(visit_id));
    }

    @Test
    public void setVisit_id() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        int visit_id = 45;
        int visit_id2 = 345;
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr,visit_id);
        myObjectUnderTest.setVisit_id(visit_id2);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getVisit_id();

        // ...then the result should be the expected one.
        assertThat(result, is(visit_id2));
    }

    @Test
    public void setSup_id() {

        String name = "Saman";
        int sup_id = 5623;
        int sup_id2 = 45567;
        String addr = "No.32, Temple Rd, Rajagiriya";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);
        myObjectUnderTest.setSup_id(sup_id2);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getSup_id();

        // ...then the result should be the expected one.
        assertThat(result, is(sup_id2));
    }

    @Test
    public void getSup_Name() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getSup_Name();

        // ...then the result should be the expected one.
        assertThat(result, is(name));
    }

    @Test
    public void setSup_Name() {
        String name = "Saman";
        String name2 = "Kamal";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);
        myObjectUnderTest.setSup_Name(name2);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getSup_Name();

        // ...then the result should be the expected one.
        assertThat(result, is(name2));
    }

    @Test
    public void getSup_Add() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        String addr2 = "No.51, Araliya Rd, Anuradhapura";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getSup_Add();

        // ...then the result should be the expected one.
        assertThat(result, is(addr));
    }

    @Test
    public void setSup_Add() {
        String name = "Saman";
        int sup_id = 5623;
        String addr = "No.32, Temple Rd, Rajagiriya";
        String addr2 = "No.51, Araliya Rd, Anuradhapura";
        Supplier myObjectUnderTest = new Supplier(sup_id,name,addr);
        myObjectUnderTest.setSup_Add(addr2);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getSup_Add();

        // ...then the result should be the expected one.
        assertThat(result, is(addr2));
    }
}