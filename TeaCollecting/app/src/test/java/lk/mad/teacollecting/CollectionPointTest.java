package lk.mad.teacollecting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CollectionPointTest {

    @Test
    public void getCp_id() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getCp_id();

        // ...then the result should be the expected one.
        assertThat(result, is(cp_id));
    }

    @Test
    public void setCp_id() {
        int cp_id = 4567;
        int cp_id2 = 754;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        myObjectUnderTest.setCp_id(cp_id2);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getCp_id();

        // ...then the result should be the expected one.
        assertThat(result, is(cp_id2));
    }

    @Test
    public void getCp_name() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getCp_name();

        // ...then the result should be the expected one.
        assertThat(result, is(cp_name));
    }

    @Test
    public void setCp_name() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        String cp_name2 = "Temple Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        myObjectUnderTest.setCp_name(cp_name2);
        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getCp_name();

        // ...then the result should be the expected one.
        assertThat(result, is(cp_name2));
    }

    @Test
    public void getLng() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getLng();

        // ...then the result should be the expected one.
        assertThat(result, is(lng));
    }

    @Test
    public void setLng() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lng2 = 25.4563463;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        myObjectUnderTest.setLng(lng2);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getLng();

        // ...then the result should be the expected one.
        assertThat(result, is(lng2));
    }

    @Test
    public void getLat() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getLat();

        // ...then the result should be the expected one.
        assertThat(result, is(lat));
    }

    @Test
    public void setLat() {
        int cp_id = 4567;
        String cp_name = "Walawwa Junction";
        double lng = 45.7856789;
        double lat = 32.4567899;
        double lat2 = 25.4563463;

        CollectionPoint myObjectUnderTest = new CollectionPoint(cp_id, cp_name, lng, lat);
        myObjectUnderTest.setLat(lat2);
        // ...when the string is returned from the object under test...
        double result = myObjectUnderTest.getLat();

        // ...then the result should be the expected one.
        assertThat(result, is(lat2));
    }
}