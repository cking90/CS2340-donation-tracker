package edu.gatech.cs2340.nonprofitdonationtracker;
import junit.framework.Assert;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.Location;


/**
 * Tests the getLocationwithKey(int key) method
 * inside of the Location class
 */
public class GetLocationWithKeyTest {

//    public static final Location def = new Location(-1,
//    "default", 0.0, 0.0, "123 main st.", "Atlanta", "Georgia",
//    12345, LocationType.WAREHOUSE, 1234567890,
//            "www.google.com");

    @Test
    public void testNoLocations() {
        Assert.assertEquals("Please set your location list to empty to run this test.",
                0, Location.getLocationList().size());
        Assert.assertNull("Since there is nothing in the list, you should return null",
                Location.getLocationWithKey(0));
    }

    @Test
    public void testKeyNotInList() {}

    @Test
    public void testKeyInList() {}

}
