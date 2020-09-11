package lk.mad.teacollecting;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void getName() {
        String name = "Saman";
        int driver_id = 5623;
        int path_id = 456;
        new Driver(driver_id,name,path_id);

        // ...when the string is returned from the object under test...
        String result = Driver.getName();

        // ...then the result should be the expected one.
        assertThat(result, is(name));
    }

    @Test
    public void getDriver_id() {
        String name = "Saman";
        int driver_id = 5623;
        int path_id = 456;
        new Driver(driver_id,name,path_id);

        // ...when the string is returned from the object under test...
        int result = Driver.getDriver_id();

        // ...then the result should be the expected one.
        assertThat(result, is(driver_id));
    }

    @Test
    public void getPath_id() {
        String name = "Saman";
        int driver_id = 5623;
        int path_id = 456;
        new Driver(driver_id,name,path_id);

        // ...when the string is returned from the object under test...
        int result = Driver.getPath_id();

        // ...then the result should be the expected one.
        assertThat(result, is(path_id));
    }
}