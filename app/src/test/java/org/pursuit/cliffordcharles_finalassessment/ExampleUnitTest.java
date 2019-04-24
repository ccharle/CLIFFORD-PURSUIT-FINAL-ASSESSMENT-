package org.pursuit.cliffordcharles_finalassessment;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pursuit.cliffordcharles_finalassessment.model.Coordinates;
import org.pursuit.cliffordcharles_finalassessment.model.Locations;
import org.pursuit.cliffordcharles_finalassessment.network.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Before
    public void setUp() throws Exception {


        //setups your environment to allow the tests to have access to your resources

    }

    @Test
    public void model_is_correct() {
        LocationsTest locationsTest = new LocationsTest("Austrailia", "AU", 1, new CoordinatesTest("23321", "-122321"));
        LocationsTest locationsTest2 = new LocationsTest("Amiling", "SS", 2, new CoordinatesTest("23321", "-122321"));
        int result = locationsTest.compareTo(locationsTest2);
        Assert.assertEquals(result, -1);
    }


    @Test
    public void model_is_incorrect() {
        LocationsTest locationsTest = new LocationsTest("Austrailia", "AU", 1, new CoordinatesTest("23321", "-122321"));
        LocationsTest locationsTest2 = new LocationsTest("Amiling", "SS", 1, new CoordinatesTest("23321", "-122321"));
        int result = locationsTest.compareTo(locationsTest2);
        Assert.assertEquals(result, 0);
    }


    @After
    public void tearDown() throws Exception {
    }

}