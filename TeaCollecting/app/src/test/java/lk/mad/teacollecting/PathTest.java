package lk.mad.teacollecting;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PathTest {

    @Test
    public void getPath_id() {
        int path_id = 34;
        String name = "Main road";
        Path myObjectUnderTest = new Path(path_id,name);

        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getPath_id();

        // ...then the result should be the expected one.
        assertThat(result, is(path_id));
    }

    @Test
    public void setPath_id() {
        int path_id = 34;
        int path_id2 = 65;
        String name = "Main road";
        Path myObjectUnderTest = new Path(path_id,name);
        myObjectUnderTest.setPath_id(path_id2);
        // ...when the string is returned from the object under test...
        int result = myObjectUnderTest.getPath_id();

        // ...then the result should be the expected one.
        assertThat(result, is(path_id2));
    }

    @Test
    public void getName() {
        int path_id = 34;
        String name = "Main road";
        Path myObjectUnderTest = new Path(path_id,name);
        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getName();

        // ...then the result should be the expected one.
        assertThat(result, is(name));
    }

    @Test
    public void setName() {
        int path_id = 34;
        String name = "Main road";
        String name2 = "Kumbura road";
        Path myObjectUnderTest = new Path(path_id,name);
        myObjectUnderTest.setName(name2);
        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getName();

        // ...then the result should be the expected one.
        assertThat(result, is(name2));
    }
}