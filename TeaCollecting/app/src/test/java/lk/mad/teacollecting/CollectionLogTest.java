package lk.mad.teacollecting;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CollectionLogTest {

    @Test
    public void getDate() {
        Date date = new Date(2020,2,22);
        double weight = 40;
        int noOfBags = 2;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);

        // ...when the string is returned from the object under test...
        Date result = myObjectUnderTest.getDate();

        // ...then the result should be the expected one.
        assertThat(result, is(date));
    }

    @Test
    public void setDate() {
        Date date = new Date(2020,2,22);
        Date date2 = new Date(2019,5,16);
        double weight = 40;
        int noOfBags = 2;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);
        myObjectUnderTest.setDate(date2);
        // ...when the string is returned from the object under test...
        Date result = myObjectUnderTest.getDate();

        // ...then the result should be the expected one.
        assertThat(result, is(date2));
    }

    @Test
    public void getWeight() {
        Date date = new Date(2020,2,22);
        double weight = 40;
        int noOfBags = 2;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getWeight();

        // ...then the result should be the expected one.
        assertThat(result, is(weight));
    }

    @Test
    public void setWeight() {
        Date date = new Date(2020,2,22);
        double weight = 40;
        double weight2 = 30;
        int noOfBags = 2;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);
        myObjectUnderTest.setWeight(weight2);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getWeight();

        // ...then the result should be the expected one.
        assertThat(result, is(weight2));
    }

    @Test
    public void getNoOfBags() {
        Date date = new Date(2020,2,22);
        double weight = 40;
        int noOfBags = 2;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getNoOfBags();

        // ...then the result should be the expected one.
        assertThat(result, is(noOfBags));
    }

    @Test
    public void setNoOfBags() {
        Date date = new Date(2020,2,22);
        double weight = 40;
        int noOfBags = 2;
        int noOfBags2 = 3;
        CollectionLog myObjectUnderTest = new CollectionLog(date,weight,noOfBags);
        myObjectUnderTest.setNoOfBags(noOfBags2);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getNoOfBags();

        // ...then the result should be the expected one.
        assertThat(result, is(noOfBags2));
    }
}